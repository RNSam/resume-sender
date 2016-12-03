package com.rns.parser;

import com.rns.company.CompanyProfile;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Map;

public interface IHTMLParser {    
    public ArrayList<CompanyProfile> parse(String URL);  
}
