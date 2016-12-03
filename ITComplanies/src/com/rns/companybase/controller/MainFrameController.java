/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rns.companybase.controller;


import com.rns.company.CompanyHTMLLoader;
import com.rns.company.CompanyProfile;
import com.rns.companybase.core.DataModel;
import com.rns.companybase.core.ViewManager;
import com.rns.companybase.process.EMailSendProcess;
import com.rns.companybase.view.MainFrame;

import common.controls.table.TableModelExt;
import common.dialog.ProgressDialog;

import common.email.MailClient;

import common.sql.SQLCondition;

import common.to.CompanyVO;

import common.utils.PropertyFile;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import javax.mail.internet.AddressException;

import javax.swing.JFileChooser;
import javax.swing.JTable;


/**
 *
 * @author R.Samoylenko
 */
public class MainFrameController extends AbstractController implements ModelChangeListener {
    private MainFrame view;
    private ArrayList<CompanyVO> companylist;

    @Override
    public void initView() {
        initViewController();
        view.setVisible(true);
    }
    
    private void initViewController() {
        view = (MainFrame)ViewManager.getInstance().getViewByController(this);         
        reloadCompanies();
        reloadMailAccounts();
    }
    
    // ======         Отбор/обновление информации на форме        ======
    
    private void reloadCompanies() {
        SQLCondition sqlc = new SQLCondition(CompanyVO.class);
        companylist = (ArrayList<CompanyVO>)DataModel.getInstance().selectExecute(sqlc);                    
        JTable companies = view.getCompanyTable();
        companies.setModel(new TableModelExt(companylist));
    }
    
    private void reloadMailAccounts() {
        Properties properties = PropertyFile.getProperties("mailAccounts");
        Set keys = properties.keySet();
        for(Object o : keys) {
            view.addMailAccount(o);
        }
    }  
    
    // Событие выбора строки в таблице
    public void companyTable() {
        int i = view.getCompanyTable().getSelectedRow();        
        view.setNameField(companylist.get(i).getNAME());
        view.setEmailField(companylist.get(i).getEMAIL());
        view.setFaceField(companylist.get(i).getFACE());
        view.setPhoneField(companylist.get(i).getPHONE());        
    }    
    
    @Override
    public void modelHasChanged() {        
        reloadCompanies();
    }

    // ======         Импорт компаний        ======
    
    public void loadFromDevBy() {
        ArrayList<CompanyProfile> cpList = CompanyHTMLLoader.getInstance().loadCompanies("https://companies.dev.by");        
        for(CompanyProfile cp : cpList) {
            CompanyVO cvo = new CompanyVO();
            cvo.setNAME(cp.getName());
            cvo.setEMAIL(cp.getEmail());
            cvo.setFACE(cp.getFace());
            cvo.setPHONE(cp.getPhone());    
            DataModel.getInstance().insertExecute(cvo);            
        }
    }
    
    // ======         Работа с сообщениями        ======
    
    public void sendMessage() {        
        JTable companies = view.getCompanyTable();
        if ((view.getEmailRecipients().equals("")) ||
            ((view.getEmailRecipients().equals("*")) && (companies.getSelectedRowCount() == 0))) return;
        
        String settingsFileName = (String)PropertyFile.getProperty("mailAccounts", view.getSelectedMailAccount().toString());                                
        String[] recipients;
        if (!view.getEmailRecipients().equals("*"))
            recipients = new String[] {view.getEmailRecipients()};                
        else { 
            recipients = new String[companies.getSelectedRowCount()];
            int j = 0;
            for (int i : companies.getSelectedRows()) {
                recipients[j++] = companylist.get(i).getEMAIL();
            }
        }        
                        
        try {            
            ProgressDialog.getInstance().startProcess(new EMailSendProcess(recipients,
                                                                           settingsFileName,
                                                                           view.getEMailTitle(),
                                                                           view.getEMailMessage(),
                                                                           view.getAttachedFilePath()), view);                
        } finally {
            ProgressDialog.getInstance().stopProcess();
        }
    }
    
    public void selectFile() {
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");                
        if (ret == JFileChooser.APPROVE_OPTION) {
           view.setAttachmentFilePath(fileopen.getSelectedFile().getAbsolutePath());
        }
    }    
      
    // ======         Действия над компаниями        ======
    
    public void addCompany() {
        ViewManager.getInstance().openView("com.rns.companybase.view.CompanyProfileView", null);
    }
    
    public void editCompany() {
        if (view.getCompanyTable().getSelectedRow() == -1) return;            
        ViewManager.getInstance().openView("com.rns.companybase.view.CompanyProfileView",
                                           companylist.get(view.getCompanyTable().getSelectedRow()));
    }

    public void deleteCompany() {
        if (view.getCompanyTable().getSelectedRow() == -1) return;   
        DataModel.getInstance().deleteExecute(companylist.get(view.getCompanyTable().getSelectedRow()));        
    }

}
