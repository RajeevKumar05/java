package com.demo.designpatterns.factory;

public class TestFactory {
	public static void main(String[] s){
		try{
			Language engLang = LangFactory.getInstance().getLanguageImpl(LangTypeConstants.ENGLISH);
			engLang.show();
			LangFactory.registerLanguageImpl("Mathematics", "com.demo.designpatterns.factory.Mathematics");
			Language mathLang = LangFactory.getInstance().getLanguageImpl("Mathematics");
			mathLang.show();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
