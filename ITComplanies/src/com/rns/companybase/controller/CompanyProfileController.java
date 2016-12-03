package com.rns.companybase.controller;


import com.rns.companybase.core.DataModel;
import com.rns.companybase.core.ViewManager;
import com.rns.companybase.view.CompanyProfileView;
import com.rns.companybase.view.CompanyProfileView.CompanyProfileActions;

import common.to.CompanyVO;


public class CompanyProfileController extends AbstractController {
    private CompanyProfileView view;
    private CompanyVO company;

    @Override
    public void initView() {                
        initViewController();
        
        if (company == null)
            view.setCurrentAction(CompanyProfileActions.CREATE);
        else view.setCurrentAction(CompanyProfileActions.EDIT);
        
        if (view.getCurrentAction() == view.getCurrentAction().EDIT) {            
            view.setCompanyName(company.getNAME().trim());
            view.setContactFace(company.getFACE().trim());
            view.setEmails(company.getEMAIL().trim());
            view.setPhones(company.getPHONE().trim());            
        }
        else company = new CompanyVO(); 
        
        view.setVisible(true);
    }
    
    public void saveButton() {
        company.setNAME(view.getCompanyName());
        company.setFACE(view.getContactFace());
        company.setEMAIL(view.getEmails());
        company.setPHONE(view.getPhones());            
        
        if (view.getCurrentAction() == view.getCurrentAction().EDIT)
            DataModel.getInstance().updateExecute(company);
        else
            DataModel.getInstance().insertExecute(company);

        resetButton();
        cancelButton();
    }
    
    public CompanyProfileController(CompanyVO aCompany) {
        super();
        company = aCompany;    
    }
    
    public CompanyProfileController() {
        company = null;
    }
    
    public void initViewController() {
        view = (CompanyProfileView)ViewManager.getInstance().getViewByController(this);
    }
    
    public void cancelButton() {       
        view.setVisible(false);
    }

    public void resetButton() {        
        view.clearFields();
    }
}
