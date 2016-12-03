/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.to;


import common.entity.struct.Field;

import java.math.BigDecimal;

import java.util.ArrayList;


/**
 *
 * @author R.Samoylenko
 */
public class CompanyVO implements IViewObject, ISingleEntity  {       
    private static final ArrayList<Field> fields;
    
    private BigDecimal id;
    private String name;
    private String email;
    private String phone;
    private String face;  
    
    public CompanyVO() { super(); }

    public void setID(BigDecimal ID) {
        id = ID;
    }
    
    public BigDecimal getID() {
        return id;
    }

    public void setNAME(String name) {
        this.name = name;
    }

    public String getNAME() {
        return name;
    }

    public void setEMAIL(String aEmail) {
        email = aEmail;
    }

    public String getEMAIL() {
        return email;
    }

    public void setPHONE(String aPhone) {
        phone = aPhone;
    }

    public String getPHONE() {
        return phone;
    }

    public void setFACE(String face) {
        this.face = face;
    }

    public String getFACE() {
        return face;
    }

    @Override
    public ArrayList<Field> getFields() {
        return fields;
    }
    
    static {
        fields = new ArrayList<Field>(0);
        fields.add(new Field("NAME","Name"));
        fields.add(new Field("FACE","Contact face"));
        fields.add(new Field("EMAIL","Email-s"));
        fields.add(new Field("PHONE","Phone"));
        fields.add(new Field("SITE","Site"));
    }
    
}
