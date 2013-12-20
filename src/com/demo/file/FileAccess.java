package com.demo.file;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAccess {
	private Properties configProp = new Properties();
	public void loadProperties() throws IOException{
		System.out.println("Loading properties.");
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("com/demo/file/config.properties");
		
		
//		try {
//            configProp.load(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
		
		
        StringBuilder sb = new StringBuilder();
		//Reader r = new InputStreamReader(in,"UTF-8");
        Reader r = new InputStreamReader(in);        
        /*{
	        char[] c = new char[1];
			for (;;) {
		        int rsz = r.read(c, 0, c.length);	        
		        if (rsz < 0)
		          break;
		        sb.append(c);
		      }
			String properties = sb.toString();
			String props[] = properties.split("\\n");		
			String key,value;
			String[] keyVal;
			String propString;
			Pattern pattern = Pattern.compile("^\\w");
			for(int i=0;i<props.length;i++){
				propString = props[i].trim();
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println(propString);
				if(pattern.matcher(propString).find()){
					System.out.println("TRUE");
					keyVal = propString.split("=");
					if(keyVal.length == 2){
						key = keyVal[0].trim();
						value = keyVal[1].trim();								
						configProp.setProperty(key, value);
					}
				}
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
			}		
        }*/
        
        {
        	BufferedReader br = new BufferedReader(r);        	
        	String line = br.readLine();
        	String prop;
        	Pattern pattern = Pattern.compile("^\\w");
        	String key,value;
			String[] keyVal;
        	while(line != null){
				prop = line.trim();				
				System.out.println(prop);
				if(pattern.matcher(prop).find()){
					System.out.println("\tIS PROPERTY = TRUE");
					keyVal = prop.split("=");
					if(keyVal.length == 2){
						key = keyVal[0].trim();
						value = keyVal[1].trim();
						System.out.println("\t$"+key+"$=$"+value+"$");
						configProp.setProperty(key, value);
					}
				}
				line = br.readLine();
        	}
        }
		
	}
	
	public void testDirectory() throws IOException{		
		String dir = this.configProp.getProperty("DIR_LOCATION");
		System.out.println("dir = "+dir);		
		String path = dir + File.separator + "test.xml";
		File f = new File(path);
		if(!f.exists()){
			f.createNewFile();
		}else{
			f.delete();
			f.createNewFile();
		}
		System.out.println("Successfully created file : "+f.getCanonicalPath());
	}
	
	public void showProperties(){
		Iterator itr = this.configProp.keySet().iterator();
		String key;
		while(itr.hasNext()){
			key = (String)itr.next();
			System.out.println(key+"="+this.configProp.getProperty(key));
		}
	}
	public static void main(String[] s) throws IOException{
		//DataInputStream is = new DataInputStream(System.in);
		//String dir = is.readLine();
		FileAccess fa = new FileAccess();
		fa.loadProperties();
		fa.testDirectory();
		System.out.println("Printing loaded properties.");
		fa.showProperties();
		
		
		
		String st = "D:\\\\TEMP\\\\XYZ";
		System.out.println(st);
//		//Pattern word = Pattern.compile("\\G\\w+");
//		Pattern word = Pattern.compile("^\\w");
//	    Matcher mat = word.matcher("this is a test 999");
//	    System.out.println(mat.find());
	}
}
