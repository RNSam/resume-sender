/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rns.companybase;

import com.rns.companybase.controller.MainFrameController;
import com.rns.companybase.core.ViewManager;

/**
 *
 * @author R.Samoylenko
 */
public class Bundle {
    
    public static void main(String[] argv) {
        System.out.println("Запуск модуля");
        ViewManager.getInstance().openView("com.rns.companybase.view.MainFrame", null);
    //    MainFrameController mfc = new MainFrameController();
    //    mfc.loadFromDevBy();
    }
    
}
