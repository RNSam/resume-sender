package parser;

public class HTMLParserFactory {    
    private static final String DEV_BY = "companies.dev.by";
    
    public static IHTMLParser getHTMLParser(String URLRoot) throws Exception {
        if (DEV_BY.indexOf(URLRoot) >= 0) 
            return new DevByHTMLParser();
        else throw new Exception("Класс для парсинга указанного URL не найден");        
    }    
}
