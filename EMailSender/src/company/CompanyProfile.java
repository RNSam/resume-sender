package company;

import java.util.ArrayList;

public class CompanyProfile {
    private String name;
    private ArrayList<String> email = new ArrayList<String>();
    private ArrayList<String> phone = new ArrayList<String>();
    private String face;  
    
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
        return email.toString();
    }

    public void addPhone(String aPhone) {
        phone.add(aPhone);
    }

    public String getPhone() {
        return phone.toString();
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getFace() {
        return face;
    }
}
