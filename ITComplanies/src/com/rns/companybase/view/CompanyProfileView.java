package com.rns.companybase.view;


import com.rns.companybase.core.GlobalListener;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CompanyProfileView extends AbstractView {
    private static final int DEF_WITH_SIZE = 500;
    private static final int DEF_HIGH_SIZE = 210;
    
    private Container contentPane;
    private JPanel fieldsPan = new JPanel();
    private GroupLayout fieldsPanelLayout = new GroupLayout(fieldsPan);   
    private JLabel companyNameLabel = new JLabel("Company Name");
    private JLabel contactFaceLabel = new JLabel("Contact face");
    private JLabel emailsLabel = new JLabel("E-Mails list");
    private JLabel phonesLabel = new JLabel("Phones list");
    private JLabel siteLabel = new JLabel("Web site");
    private JTextField companyNameTF = new JTextField(30);
    private JTextField contactFaceTF = new JTextField(30);
    private JTextField emailsTF = new JTextField(30);
    private JTextField phonesTF = new JTextField(30);
    private JTextField siteTF = new JTextField(30);
    
    private JPanel buttonsPan = new JPanel();
    private JButton saveButton = new JButton("Save");
    private JButton cancelButton = new JButton("Cancel");
    private JButton resetButton = new JButton("Reset");
    
    private CompanyProfileActions currentAction;

    public CompanyProfileActions getCurrentAction() {
        return currentAction;
    }
    
    public void setCurrentAction(CompanyProfileActions aAction) {
        currentAction = aAction;
        setTitle(currentAction.caption);
    }
    
    public static enum CompanyProfileActions { 
        CREATE ("New company"),
        EDIT ("Edit company");
        
        private final String caption;        
        CompanyProfileActions(String aCaption) {caption = aCaption;}        
        String getCaption() {return caption;}
    };
    
    public CompanyProfileView(){
        super("");
        buildView();
    }
    
    public CompanyProfileView(CompanyProfileActions aAction) {        
        super(aAction.getCaption());
        currentAction = aAction;           
    }
    
    private void buildView() {
        setSize(DEF_WITH_SIZE, DEF_HIGH_SIZE);
        setLocationRelativeTo(null);            
        setResizable(false);            
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
                
        
        fieldsPan.setLayout(fieldsPanelLayout);           
        fieldsPanelLayout.setAutoCreateGaps(true);
        fieldsPanelLayout.setAutoCreateContainerGaps(true);
        fieldsPanelLayout.setHorizontalGroup(
        fieldsPanelLayout.createSequentialGroup()
                .addGroup(fieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(companyNameLabel)
                    .addComponent(contactFaceLabel)
                    .addComponent(emailsLabel)
                    .addComponent(phonesLabel)
                    .addComponent(siteLabel))
                .addGroup(fieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(companyNameTF)
                    .addComponent(contactFaceTF)
                    .addComponent(emailsTF)
                    .addComponent(phonesTF)
                    .addComponent(siteTF))
        );
        fieldsPanelLayout.setVerticalGroup(
                fieldsPanelLayout.createSequentialGroup()
                    .addGroup(fieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(companyNameLabel)
                        .addComponent(companyNameTF))
                    .addGroup(fieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(contactFaceLabel)
                        .addComponent(contactFaceTF))
                    .addGroup(fieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(emailsLabel)
                        .addComponent(emailsTF))
                    .addGroup(fieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(phonesLabel)
                        .addComponent(phonesTF))
                    .addGroup(fieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(siteLabel)
                        .addComponent(siteTF))
                
        );
        
              
//        contentPane.setLayout(new BorderLayout());        
//        ((BorderLayout)contentPane.getLayout()).setVgap(10);
//        fieldsPan.setLayout(new GridLayout(0, 2));
//        fieldsPan.add(companyNameLabel);
//        fieldsPan.add(companyNameTF);         
//        fieldsPan.add(contactFaceLabel);
//        fieldsPan.add(contactFaceTF);
//        fieldsPan.add(emailsLabel);
//        fieldsPan.add(emailsTF);
//        fieldsPan.add(phonesLabel);
//        fieldsPan.add(phonesTF);
//        fieldsPan.add(siteLabel);
//        fieldsPan.add(siteTF);
                    
        saveButton.setName("saveButton");
        cancelButton.setName("cancelButton");
        resetButton.setName("resetButton");
        resetButton.addActionListener(GlobalListener.getInstance());
        saveButton.addActionListener(GlobalListener.getInstance());
        cancelButton.addActionListener(GlobalListener.getInstance());
        
        buttonsPan.add(resetButton);
        buttonsPan.add(saveButton);        
        buttonsPan.add(cancelButton);
        
        contentPane.add(fieldsPan, BorderLayout.CENTER);        
        contentPane.add(buttonsPan, BorderLayout.SOUTH);           
    }
    
    public String getCompanyName() {
        return companyNameTF.getText().trim();
    }
    
    public void setCompanyName(String aValue) {
        companyNameTF.setText(aValue);
        if (aValue.length()>0)
            companyNameTF.setCaretPosition(1);
    }
    
    public String getContactFace() {
        return contactFaceTF.getText().trim();
    }
    
    public void setContactFace(String aValue) {
        contactFaceTF.setText(aValue);
        if (aValue.length()>0)
            contactFaceTF.setCaretPosition(1);
    }
    
    public String getEmails() {
        return emailsTF.getText().trim();
    }
    
    public void setEmails(String aValue) {
        emailsTF.setText(aValue);
        if (aValue.length()>0)
            emailsTF.setCaretPosition(1);
    }
    
    public String getPhones() {
        return phonesTF.getText().trim();
    }
    
    public void setPhones(String aValue) {
        phonesTF.setText(aValue);
        if (aValue.length()>0)
            phonesTF.setCaretPosition(1);
    }
    
    public String getSite() {
        return siteTF.getText().trim();
    }
    
    public void setSite(String aValue) {
        siteTF.setText(aValue);
        if (aValue.length()>0)
            siteTF.setCaretPosition(1);
    }
    
    public void clearFields() {
        companyNameTF.setText("");
        contactFaceTF.setText("");
        emailsTF.setText("");
        phonesTF.setText("");
        siteTF.setText("");
    }
    
}
