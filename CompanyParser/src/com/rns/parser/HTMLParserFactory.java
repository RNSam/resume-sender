package com.rns.parser;

public class HTMLParserFactory {    
    private static final String DEV_BY = "companies.dev.by";
    
    public static IHTMLParser getHTMLParser(String URLRoot) throws Exception {
        if (URLRoot.indexOf(DEV_BY) >= 0) 
            return new DevByHTMLParser();
        else throw new Exception("Класс для парсинга указанного URL не найден");        
    }    
}
