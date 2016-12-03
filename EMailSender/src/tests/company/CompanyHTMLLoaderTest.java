/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.company;

import company.CompanyHTMLLoader;
import company.CompanyProfile;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author R.Samoylenko
 */
public class CompanyHTMLLoaderTest {
    
    
    public CompanyHTMLLoaderTest() {
    }

    @Test
    public void testGetInstance() {
    }

    @Test
    public void testLoadCompanies() {
        ArrayList<CompanyProfile> list = CompanyHTMLLoader.getInstance().loadCompanies("companies.dev.by");
    }
    
}
