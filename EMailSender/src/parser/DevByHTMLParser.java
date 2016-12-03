package parser;

import company.CompanyProfile;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.Set;

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

    private CompanyProfile parseProfileFromPage(String aFileName, String URL) throws IOException {
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
        cp.setName(CompanyName.text());
        
        return null;
    }

    @Override
    public ArrayList<CompanyProfile> parse(String URL) {
        ArrayList<CompanyProfile> resultSet = null;
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
            }
        }
        
        return resultSet;
    }
}
