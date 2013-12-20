package com.demo.adt;

//jdk
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

//oracle.ide.xmlef.jar
import oracle.bali.xml.addin.XMLSourceNode;
import oracle.bali.xml.addin.wizard.XMLStringFormatter;

//xmleditor.jar
import oracle.bali.xml.dom.XmlDeclarationInfo;
import oracle.bali.xml.model.AbstractModel;
import oracle.bali.xml.model.XmlMetadataResolver;
import oracle.bali.xml.model.XmlModel;
import oracle.bali.xml.model.task.FixedNameTransactionTask;
import oracle.bali.xml.model.task.NonValidatingTransactionTask;

//xmlef.jar
import oracle.bali.xml.grammar.AttributeDef;
import oracle.bali.xml.grammar.ComplexType;
import oracle.bali.xml.grammar.ContentGroup;
import oracle.bali.xml.grammar.ElementDef;
import oracle.bali.xml.grammar.Grammar;
import oracle.bali.xml.grammar.GrammarComponent;
import oracle.bali.xml.grammar.GrammarProvider;
import oracle.bali.xml.grammar.QualifiedName;
import oracle.bali.xml.grammar.SubstitutionGroup;
import oracle.bali.xml.grammar.Type;
import oracle.bali.xml.grammar.resolver.GrammarResolver;
import oracle.bali.xml.grammar.util.AttributeValueUtils;
import oracle.bali.xml.metadata.DomNodeXmlKey;
import oracle.bali.xml.metadata.MetadataProvider;
import oracle.bali.xml.metadata.XmlKey;
import oracle.bali.xml.metadata.XmlMetadataConstants;
import oracle.bali.xml.metadata.grammar.GrammarMetadataProvider;


//oracle.ide.jar
import oracle.ide.model.NodeFactory;
import oracle.ide.model.Project;


//jdk
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class SchemaBasedXMLBuilder 
{
  public SchemaBasedXMLBuilder(
    Project project,
    URL filename,
    Grammar grammar, 
    GrammarProvider grammarProvider,    
    ElementDef root, 
    int depth, 
    boolean reqElems,
    boolean registeredSchema,
    String encoding)
  {
    _project = project;
    _filename = filename;
    _grammar = grammar;
    _root = root;
    _depth = depth;
    _onlyReqElems = reqElems;
    _registeredSchema = registeredSchema;
    _encoding = encoding;

    _grammarProvider = grammarProvider;
    _grammarResolver = new GrammarResolver(_grammarProvider);
    _metadataProvider = new GrammarMetadataProvider(_grammarResolver);
  }
  
  public void build()
  {
    QualifiedName qn = _root.getQualifiedName();
    
    final XmlModel model = _getModelFromSource(qn);
    _resolver = model.getXmlMetadataResolver();

    model.getDomModel().setXmlDeclarationInfo(new XmlDeclarationInfo(
                                                XmlDeclarationInfo.VERSION_1_0,
                                                _encoding,
                                                null));

    new FixedNameTransactionTask("Create Xml Document Root")  //NOTRANS
    {
      protected void performTask(AbstractModel ignoreParam)
      {
        _doc = model.getDocument();
        
        // Lets hear it for null-document support !
        //Element e = _doc.getDocumentElement();
        
        Element[] roots = 
          _createDOMElement(_doc, _root, ContentGroup.VARIETY_UNKNOWN, 0, 0, false); 
          
        Element e = roots[0];

        _setAttributeValues(e);

        if( !_registeredSchema)
        {
          // If we're working with a non-registered schema, we set XSI attributes
          // so that we can have code insight on the instance.
          e.setAttributeNS(_XMLNS_NAMESPACE, _XML_XSI, _XMLXSI_NAMESPACE);
          
          String targetNameSpace = _grammar.getTargetNamespace();
          String relativeSchemaFilename = model.getContext().getRelativePathForURL(_grammar.getLocation());
          if( targetNameSpace != null)
          {
            String schemaLocation = targetNameSpace + " " + relativeSchemaFilename; // NOTRANS
            e.setAttributeNS(_XMLXSI_NAMESPACE, _XML_SCHEMA_LOC, schemaLocation);
          }
          else
          {
            e.setAttributeNS(_XMLXSI_NAMESPACE, _XML_NNS_SCHEMA_LOC, relativeSchemaFilename);
          }
        }

        model.fixPrefixes(_doc, e);

        _doc.appendChild(e);
      }
      
      protected Logger getLogger(AbstractModel model)
      {
        return _LOG;
      }
    }.run(model);
    
    // We do this operation in two separate transactions so that
    // the XSI instance stuff can be instantiated, so that when we
    // create the child elements, they take full advantage of any
    // available metadata.
    new NonValidatingTransactionTask() //NOTRANS
    {
      protected void performTask(AbstractModel ignoreParam)
      {
        _doc = model.getDocument();
        
        Element e = _doc.getDocumentElement();
        
        handleElementDef(e, _root, 0, model);
      }
      
      protected String getTransactionNameWithoutModelAccess()
      {
        return "Create Xml Document Body"; // NOTRANS
      }
      
      protected Logger getLogger(AbstractModel model)
      {
        return _LOG;
      }
    }.run(model);
  }
  
  protected void handleElementDef(Element parent, ElementDef e, int depth, XmlModel model)
  {
    boolean namespaceChange = false;
    String oldNamespace = null;
    if (!_namespaces.isEmpty())
      oldNamespace = (String) _namespaces.peek();
    String namespace = e.getQualifiedName().getNamespace();
    
    if ((oldNamespace == null && namespace != null) ||
        (namespace != null && !oldNamespace.equals(namespace)))
    {
      parent.setAttributeNS(_XMLNS_NAMESPACE, _XML_NS, namespace);
      _namespaces.push(namespace);
      namespaceChange = true;
    }
  
    Type t = e.getType();
    
    if (t instanceof ComplexType)
    {
      ComplexType cType = (ComplexType) t;
      ContentGroup cg = cType.getContentGroup();
      handleContentGroup(parent, e, cg, cg.getVariety(), depth, model, null);
    }
    
    if (namespaceChange)
    {
      _namespaces.pop();
    }
  }
  
  protected void handleContentGroup(
    Element parent, 
    ElementDef e, 
    ContentGroup cg,
    int variety, 
    int depth,
    XmlModel model,
    List<Element> grpElems)
  {
    // Dont build past the required depth
    if (depth >= _depth)
      return;
  
    Collection components = cg.getComponents();
    Iterator compItor = components.iterator();
    int childCount = 0;
    boolean inChoice = (grpElems != null);
    
    while (compItor.hasNext())
    {
      GrammarComponent component = (GrammarComponent) compItor.next();
      
      if (component instanceof ContentGroup)
      {
        ContentGroup subGroup = (ContentGroup) component;
        int subVariety = subGroup.getVariety();
        
        boolean writeComment = false;
        List<Element> subGrpElems = grpElems;
        if (subGrpElems == null && variety == ContentGroup.VARIETY_GROUP_CHOICE &&
            childCount > 0)
        {
          subGrpElems = new ArrayList<Element>();
          writeComment = true;
        }
        handleContentGroup(parent, e, subGroup, subVariety, depth, model, subGrpElems);
        childCount++;
        
        if (writeComment && !subGrpElems.isEmpty())
        {
          _generateCommentNode(parent, depth, subGrpElems);
        }
      }
      else if (component instanceof ElementDef)
      {
        ElementDef childDef = (ElementDef) component;
      
        if (!childDef.isAbstract())
        {
          Element[] newElems = _createDOMElement(parent, childDef, variety, 
                                                 childCount++, depth, inChoice);
          if (newElems != null)
          {
            if (!inChoice)
            {
              for (int i=0; i<newElems.length; i++)
              {
                Element newElem = newElems[i];
                _setAttributeValues(newElem);
                model.fixPrefixes(parent, newElem);
                parent.appendChild(newElem);
                handleElementDef(newElem, childDef, depth + 1, model);
              }
            }
            else
            {
              for (int i=0; i<newElems.length; i++)
              {
                grpElems.add(newElems[i]);
              }
            }
          }
        }
        
        SubstitutionGroup substGroup = childDef.getSubstitutionGroup();
        Iterator substElems = substGroup.getElementDefs().iterator();
        while (substElems.hasNext())
        {
          ElementDef subElem = (ElementDef) substElems.next();
          Element[] newElems = _createDOMElement(parent, subElem, 
                                                 ContentGroup.VARIETY_GROUP_CHOICE, 
                                                 childCount++, depth, inChoice);
          if (newElems != null)
          {
            if (!inChoice)
            { 
              for (int i=0; i<newElems.length; i++)
              {
                Element newElem = newElems[i];
                _setAttributeValues(newElem);
                model.fixPrefixes(parent, newElem);
                parent.appendChild(newElem);
                handleElementDef(newElem, subElem, depth + 1, model);
              }
            }
            else 
            {
              for (int i=0; i<newElems.length; i++)
              {
                grpElems.add(newElems[i]);
              }
            }
          }
        }
      }
    }
  }
  
  /**
   * Sets the attribute values on the <code>Element</code> based on information
   * provided by the metadata. This method will always try to use the 
   * <code>XmlMetadataResolver</code> first, but then fall back to using the
   * <code>MetadataProvider</code> only if the resolver does not exist or could
   * not be obtained.
   * 
   * @param e The element to fix attribute values on
   */
  private void _setAttributeValues(Element e)
  {
    NamedNodeMap attrs = e.getAttributes();
    
    for (int i=0; i<attrs.getLength(); i++)
    {
      Attr newAttr = (Attr) attrs.item(i);
      
      // try to get initial value
      XmlKey key = DomNodeXmlKey.createImmutableXmlKey(_grammarResolver, 
                                                             (Node) newAttr);
      String value = null;
      
      // try the resolver first
      if (_resolver != null)
      {
        value = _resolver.getInitialValue(key);
      }
      
      // then fall back to md provider
      else
      {
        value = (String) _metadataProvider.getMetadataItem(
          key, XmlMetadataConstants.ATTRIBUTE_INITIALVALUE);
      }
      
      if (value != null)
      {
        newAttr.setNodeValue(value);
      }
    }
  }
  
  /**
   * Creates a new DOM <code>Element</code> that handles the creation of 
   * required attributes and respects min/max occurs. If the element belongs
   * in a substition group, other elements are also created, but commented out
   * (much in the same way that choice works). Min occurs is handled by first
   * creating the node template, than cloning it.
   * 
   * @param e The ElementDef to build the Element from.
   * @param variety The variety of the content group.
   * @param num The current index of this element with respect to its parent's
   * children
   * @return Array of Elements based on the grammar component
   */
  private Element[] _createDOMElement(
    Node parent, 
    ElementDef e, 
    int variety, 
    int num,
    int depth,
    boolean inChoice) // will remove this when text sync becomes better for comments
  {
    int minOccurs = e.getMinOccurs();
  
    // If we are only building required elements, and the minOccurs is set to
    // 0, then do not build this element
    if (_onlyReqElems && minOccurs <= 0)
    {
      return null;
    }
    
    // Create the element
    QualifiedName qn = e.getQualifiedName();
    Element ref = _doc.createElementNS(qn.getNamespace(), qn.getName());
    
    // Go through the attributes and find which ones we should create
    Iterator attrItor = e.getAttributeDefs().iterator();
    while (attrItor.hasNext())
    {
      AttributeDef attr = (AttributeDef) attrItor.next();
      if (attr.isRequired())
      {
        QualifiedName aQn = attr.getQualifiedName();
        
        // create required attributes
        Attr newAttr = 
          _doc.createAttributeNS(aQn.getNamespace(), aQn.getName());
        
        // attempt to get a default value. we will later replace this value
        // with metadata provided values if they exist
        String defaultValue = attr.getFixedValue();
        String value = defaultValue != null ?
          defaultValue :
          attr.getDefaultValue();
        
        // see if we can get a value from contraining facets
        if (value == null)
        {
          Set possibleValues = AttributeValueUtils.getAttributeValues(attr);
          
          // randomly pick the first if its available
          if (!possibleValues.isEmpty())
          {
            value = (String) possibleValues.iterator().next();
          }
        }
        
        if (value != null)
        {
          newAttr.setNodeValue(value);
        }
        
        ref.setAttributeNodeNS(newAttr);
      }
    }
    
    Element[] ret = null;
    
    if (minOccurs <= 1)
    {
      ret = new Element[] { ref };
    }

    // If we need to create more than one element, clone the element
    else
    {
      ret = new Element[minOccurs];
      for (int i=0; i<minOccurs; i++)
      {
        ret[i] = (Element) ref.cloneNode(true);
      }
    }
    
    // If the element is superfulous, comment it out (leave in if in sub-choice)    
    if (!inChoice && variety == ContentGroup.VARIETY_GROUP_CHOICE && num > 0)
    {
      StringBuffer commentContents = new StringBuffer();
      
      for (int i=0; i<ret.length; i++)
      {
        XMLStringFormatter.processElement(ret[i], commentContents, depth + 1, true);
      }
      
      Comment com = _doc.createComment(commentContents.toString());
      parent.appendChild(com);
      
      return null;
    }
    
    return ret;
  }
  
  private void _generateCommentNode(Element parent, int depth, List<Element> grpElems)
  {
    StringBuffer commentContents = new StringBuffer();
    
    for(Element elem : grpElems)
    {
      XMLStringFormatter.processElement(elem, commentContents, depth + 1, true);
    }
    
    Comment com = _doc.createComment(commentContents.toString());
    parent.appendChild(com);
  }
  
  private XmlModel _getModelFromSource(QualifiedName startingElement)
  {
    oracle.ide.model.Node n = null;
    
    // Create the XML soure node 
    try
    {
      //Bug# 8293302 - SAVE WITHOUT FILE EXTENSION FAILS FOR XML FOR SCHEMA.
      //Class cast exception caused by casting UnrecognizedTextNode to XmlSourceNode
      //when no extension is specified in _filename.  
      //Use a default node type  of XMLSourceNode when the contents of  _filename is not recognized
      n = NodeFactory.findOrCreateUsingDefault(_filename, XMLSourceNode.class);      
    }
    catch (InstantiationException e)
    {
      _logException(e);
    }
    catch (IllegalAccessException e)
    {
      _logException(e);
    }
    
    XMLSourceNode xmlNode = (XMLSourceNode) n;
        
    // Lets hear it for null-document support !
    /*String charString = FastMessageFormat.formatMessage(
      _FIRST_ROOT_TEMPLATE, 
      new String[] {startingElement.getNamespace(), startingElement.getName()});
    final char[] outputChars = charString.toCharArray();
    
    // Insert the root element so that we do not have a null-document
    EditProcessor.doReplaceAll(
      outputChars, n, false, "XML Schema-Based Wizard", this);  //NOTRANS*/
    
    XmlModel model = xmlNode.getModel(_project);
    
    return model;
  }
  
  /**
   * Logs an exception at the WARNING level.
   * 
   * @param t The exception (or throwable) to log.
   */
  private void _logException(Throwable t)
  {
    _LOG.log(Level.WARNING, t.getLocalizedMessage(), t);
  }
  
  private static final String _XML_NS       = "xmlns";    // NOTRANS
  private static final String _XML_XSI      = "xmlns:xsi"; // NOTRANS
  private static final String _XML_SCHEMA_LOC = "xsi:schemaLocation"; // NOTRANS
  private static final String _XML_NNS_SCHEMA_LOC = "xsi:noNamespaceSchemaLocation"; // NOTRANS

  private static final String _XMLNS_NAMESPACE = 
    "http://www.w3.org/2000/xmlns/"; // NOTRANS
  private static final String _XMLXSI_NAMESPACE =
    "http://www.w3.org/2001/XMLSchema-instance"; // NOTRANS

  private static final Logger _LOG = Logger.getLogger(
    "oracle.bali.xml.addin.wizard.SchemaBasedXMLBuilder");  // NOTRANS
    
  
  private final Project _project;
  private final URL _filename;
  private final Grammar _grammar;
  private final ElementDef _root;
  private final MetadataProvider _metadataProvider;
  private final GrammarProvider _grammarProvider;
  private final GrammarResolver _grammarResolver;
  private XmlMetadataResolver _resolver;
  private Document _doc;
  private final int _depth;
  private final boolean _onlyReqElems;
  private final boolean _registeredSchema;
  private final Stack _namespaces = new Stack();
  private final String _encoding;
}
