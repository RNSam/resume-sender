package company;

import java.util.ArrayList;

import parser.HTMLParserFactory;
import parser.IHTMLParser;

public class CompanyHTMLLoader {
    private static CompanyHTMLLoader instance  = new CompanyHTMLLoader();
    
    private CompanyHTMLLoader() { super(); }
    
    public static CompanyHTMLLoader getInstance() {
        return instance;
    }
    
    public ArrayList<CompanyProfile> loadCompanies(String rootURL) {
        IHTMLParser hTMLParser = null;
        try {
            hTMLParser = HTMLParserFactory.getHTMLParser(rootURL);
        } catch (Exception e) {
        }
        return hTMLParser.parse(rootURL);
    }
    
    public static void main(String[] args) {
        ArrayList<CompanyProfile> list = 
                CompanyHTMLLoader.getInstance().loadCompanies("companies.dev.by");
    }
}
