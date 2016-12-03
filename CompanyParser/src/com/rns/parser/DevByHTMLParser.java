package com.rns.parser;

import com.rns.company.CompanyProfile;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DevByHTMLParser implements IHTMLParser {
    public DevByHTMLParser() { super(); }

    private Map<String, String> getLinksFromPage(String aFileName, String URL) throws IOException {
        Document doc = null;
        Map<String, String> companies = new HashMap<String, String>();
        if (aFileName == null)
            doc = Jsoup.connect(URL).get();
        else {
            File input = new File(aFileName);
            doc = Jsoup.parse(input, "UTF-8", URL);        
        }
        
        Element eTable = doc.body().getElementById("tablesort");
        Elements elements = eTable.select("tbody");
        for (Element row : elements.select("tr")) {
            Elements col = row.select("td");                        
            Elements link = col.select("a");                        
            companies.put(col.get(0).text(), link.get(0).attr("abs:href"));
        }        
        return companies;
    }

    private CompanyProfile parseProfileFromPage(String aFileName, String URL) throws IOException, Exception {
        Document doc = null;
        CompanyProfile cp = new CompanyProfile();
        
        if (aFileName == null)
            doc = Jsoup.connect(URL).get();
        else {
            File input = new File(aFileName);
            doc = Jsoup.parse(input, "UTF-8", URL);        
        }
        
        Elements emails = doc.body().select("a[href*=mailto:]");        
        for (Element email : emails) {
            cp.addEmail(email.text());
        }
        Elements phones = doc.body().select("span:contains(+375)"); 
        for (Element phone : phones) {
            cp.addPhone(phone.text());
        }
        Elements CompanyName = doc.body().select("div[class=fn org hidden]"); 
        if (CompanyName.text().length() == 0) {
            Elements h2 = doc.body().select("h2");
            for (Element el : h2) {
                if (el.nextElementSibling().className().equalsIgnoreCase("full-name"))
                        cp.setName(el.text());
            }
        }
        else cp.setName(CompanyName.text());
        
        validate(cp, URL);
        
        return cp;
    }

    @Override
    public ArrayList<CompanyProfile> parse(String URL) {
        ArrayList<CompanyProfile> resultSet = new ArrayList<CompanyProfile>(0);
        Map<String, String> companies =  null;
        try {
            companies = getLinksFromPage(null, URL);
        } catch (IOException e) {
        }
        
        Set<String> keys = companies.keySet();        
        for(String key : keys) {            
            try {
                resultSet.add(parseProfileFromPage(null, (String)companies.get(key)));                
            } catch (IOException e) {
            } catch (Exception ex) {}
        }
        
        return resultSet;
    }
    
    private void validate(CompanyProfile profile, String URL) throws Exception {
        if (profile.getName().length() == 0) {
            Logger.getLogger(DevByHTMLParser.class.getName()).log(Level.SEVERE, "'NAME' is NULL: URL = ".concat(URL));            
        }
        if (profile.getEmail().length() == 0) {
            Logger.getLogger(DevByHTMLParser.class.getName()).log(Level.SEVERE, "'EMAIL' is NULL: URL = ".concat(URL));            
        }
        if ((profile.getName().length() == 0) || (profile.getEmail().length() == 0)) throw new Exception();
    }
}
