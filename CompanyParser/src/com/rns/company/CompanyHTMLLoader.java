package com.rns.company;

import java.util.ArrayList;

import com.rns.parser.HTMLParserFactory;
import com.rns.parser.IHTMLParser;

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
}
