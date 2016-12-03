package com.rns.company;

import java.util.ArrayList;

public class CompanyProfile {
    private String name;
    private ArrayList<String> email = new ArrayList<String>();
    private ArrayList<String> phone = new ArrayList<String>();
    private String face;
    private String siteURL;
    
    public CompanyProfile() { super(); }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addEmail(String aEmail) {
        email.add(aEmail);
    }

    public String getEmail() {
        StringBuffer sb = new StringBuffer(" ");
        for (String s : email) sb.append(s).append("; ");
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    public void addPhone(String aPhone) {
        phone.add(aPhone);
    }

    public String getPhone() {
        StringBuffer sb = new StringBuffer(" ");
        for (String s : phone) sb.append(s).append("; ");        
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getFace() {
        return face;
    }
    
    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }

    public String getSiteURL() {
        return siteURL;
    }
}
