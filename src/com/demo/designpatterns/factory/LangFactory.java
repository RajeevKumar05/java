package com.demo.designpatterns.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.demo.exception.LanguageAlreadyExistsException;
import com.demo.exception.LanguageNotFound;

public class LangFactory {
	private static LangFactory instance = null;
	private static HashMap<String,String> classMap = new HashMap<String,String>();
	static{
		classMap.put(LangTypeConstants.ENGLISH, "com.demo.designpatterns.factory.English");
	}
	public static void registerLanguageImpl(String lang,String langImpClass) throws LanguageAlreadyExistsException{
		if(classMap.get(lang) != null){
			throw new LanguageAlreadyExistsException("Language "+lang+" already exists.");
		}else{
			classMap.put(lang, langImpClass);
		}
	}
	public Language getLanguageImpl(String lang) throws LanguageNotFound, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		String langImplClass = classMap.get(lang);
		if(langImplClass == null || "".equalsIgnoreCase(langImplClass))
			throw new LanguageNotFound("Language "+lang+" is not registered.");
		else{
			Class<?> cls = Class.forName(langImplClass);
			Class<?>[] paramTypes = null;
			Constructor construct = cls.getConstructor(paramTypes);
			Object[] initParams = null;
			return (Language)construct.newInstance(initParams);
		}
	}
	public static LangFactory getInstance(){
		if(instance!=null){
			return instance;
		}
		else{
			return new LangFactory();
		}
	}
	
}
