/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rns.companybase.view;

import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author R.Samoylenko
 */
public class AbstractView extends JFrame {

    public AbstractView(String string) throws HeadlessException {
        super(string);
    }
    
}
