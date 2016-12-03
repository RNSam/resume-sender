/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rns.companybase.view;


import com.rns.companybase.core.GlobalListener;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 *
 * @author R.Samoylenko
 */
public class MainFrame extends AbstractView {
    private static final String FRAME_CAPTION = "Company base - Main frame";

    private static final int DEF_WITH_SIZE = 800;
    private static final int DEF_HIGH_SIZE = 800;
    
    // ==================================================
    //             MAIN MENU
    // ==================================================
    
    JMenuBar mainMenu = new JMenuBar();
    JMenu importMenu = new JMenu("Import");
    JMenuItem devbyImport = new JMenuItem("Site: dev.by");
            
    private Container contentPane;
    private JPanel mainClientPan = new JPanel();
    
    // ==================================================
    //             COMPANY PANEL
    // ==================================================
    private JPanel companyPan = new JPanel();
    private JPanel profilePanel = new JPanel();
    private JPanel profileBtnPanel = new JPanel();
    
    private GroupLayout profilePanelLayout = new GroupLayout(profilePanel);    
    
    private JLabel nameLabel = new JLabel("Company Name");
    private JTextField nameField = new JTextField();    
    private JLabel emailLabel = new JLabel("E-mail");
    private JTextField emailField = new JTextField();
    private JLabel faceLabel = new JLabel("Contact face");
    private JTextField faceField = new JTextField();
    private JLabel phoneLabel = new JLabel("Phones");
    private JTextField phoneField = new JTextField();
    private JLabel siteLabel = new JLabel("Site link");
    private JTextField siteField = new JTextField();
    
    private JTable companyTable = new JTable();
    private JScrollPane scrollTable = new JScrollPane(companyTable);
    
    private JButton addCompany = new JButton("Add");
    private JButton editCompany = new JButton("Edit");
    private JButton deleteCompany = new JButton("Delete");
        
    // ==================================================
    //             MAIL CLIENT PANEL
    // ==================================================
    private JPanel mailClientPan = new JPanel();
    private JPanel headerFieldsPanel = new JPanel();
    private JPanel headerBtnPanel = new JPanel();
    private GroupLayout headerFieldsPanelLayout = new GroupLayout(headerFieldsPanel);
    private JPanel headerPanel = new JPanel();
    private JLabel fromLabel = new JLabel("From");
    private JComboBox fromField = new JComboBox();    
    private JLabel toLabel = new JLabel("To");
    private JTextField toField = new JTextField();
    private JLabel titleLabel = new JLabel("Title");
    private JTextField titleField = new JTextField();
    private JLabel attachLabel = new JLabel("Attachment");
    private JTextField attachPathField = new JTextField();
    private JPanel attachPanel = new JPanel();
    private JButton sendMessage = new JButton("Send");  
    private JButton selectFile = new JButton("Select File");  
    
    private JTextArea messageText = new JTextArea();
    
    private JSplitPane splitPan =
        new JSplitPane(JSplitPane.VERTICAL_SPLIT, companyPan, mailClientPan);
    private Dimension minimumSize = new Dimension(100, 100);

    
    public MainFrame() throws HeadlessException {    
        super(FRAME_CAPTION);
        
        setComponentNames(); 
        buildView();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    }
    
    private void buildView() {        
        setSize(DEF_WITH_SIZE, DEF_HIGH_SIZE);
        // расположить окно по центру
        setLocationRelativeTo(null);
        
        mainMenu.add(importMenu);
        importMenu.add(devbyImport);        
        setJMenuBar(mainMenu);
        
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(splitPan, BorderLayout.CENTER);
        splitPan.setDividerLocation(400);
        
        // ==================================================
        //             COMPANY PANEL BUILD
        // ==================================================
        
        nameField.setEditable(false);
        faceField.setEditable(false);
        emailField.setEditable(false);
        phoneField.setEditable(false);
        siteField.setEditable(false);
        
        profilePanel.setLayout(profilePanelLayout);
        profilePanelLayout.setAutoCreateGaps(true);
        profilePanelLayout.setAutoCreateContainerGaps(true);
        profilePanelLayout.setHorizontalGroup(
                profilePanelLayout.createSequentialGroup()
                    .addGroup(profilePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel)
                        .addComponent(faceLabel)
                        .addComponent(emailLabel)
                        .addComponent(phoneLabel)
                        .addComponent(siteLabel))
                    .addGroup(profilePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameField)
                        .addComponent(faceField)
                        .addComponent(emailField)
                        .addComponent(phoneField)
                        .addComponent(siteField))
        );
        profilePanelLayout.setVerticalGroup(
                profilePanelLayout.createSequentialGroup()
                    .addGroup(profilePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameField))
                    .addGroup(profilePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(faceLabel)
                        .addComponent(faceField))
                    .addGroup(profilePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(emailLabel)
                        .addComponent(emailField))
                    .addGroup(profilePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(phoneLabel)
                        .addComponent(phoneField))
                    .addGroup(profilePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(siteLabel)
                        .addComponent(siteField))
        );             
        
        profileBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        profileBtnPanel.setBorder(BorderFactory.createEmptyBorder( 10, 10, 10, 10 ));
        profileBtnPanel.add(addCompany);
        profileBtnPanel.add(editCompany);
        profileBtnPanel.add(deleteCompany);                           
        scrollTable.setBorder(BorderFactory.createEtchedBorder());
        
        // ==================================================
        //             EMAIL PANEL BUILD
        // ==================================================        
        
        attachPanel.setLayout(new BorderLayout());
        attachPanel.add(selectFile, BorderLayout.EAST);
        attachPanel.add(attachPathField, BorderLayout.CENTER);
        
        headerFieldsPanel.setLayout(headerFieldsPanelLayout);
        headerFieldsPanelLayout.setAutoCreateGaps(true);
        headerFieldsPanelLayout.setAutoCreateContainerGaps(true);
        headerFieldsPanelLayout.setHorizontalGroup(
                headerFieldsPanelLayout.createSequentialGroup()
                    .addGroup(headerFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fromLabel)
                        .addComponent(toLabel)
                        .addComponent(titleLabel)
                        .addComponent(attachLabel))
                    .addGroup(headerFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fromField)
                        .addComponent(toField)
                        .addComponent(titleField)
                        .addComponent(attachPanel))
        );
        headerFieldsPanelLayout.setVerticalGroup(
                headerFieldsPanelLayout.createSequentialGroup()
                    .addGroup(headerFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(fromLabel)
                        .addComponent(fromField))
                    .addGroup(headerFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(toLabel)
                        .addComponent(toField))
                    .addGroup(headerFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(titleLabel)
                        .addComponent(titleField))
                    .addGroup(headerFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(attachLabel)
                        .addComponent(attachPanel))
        );                
        
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(headerFieldsPanel, BorderLayout.CENTER);    
        headerBtnPanel.setLayout(new GridBagLayout());        
        headerBtnPanel.add(sendMessage);             
        headerPanel.add(headerBtnPanel, BorderLayout.EAST);        
        
        mailClientPan.setBorder(new EmptyBorder(10, 10, 10, 10));
        mailClientPan.setLayout(new BorderLayout(0,0));
        mailClientPan.add(headerPanel, BorderLayout.NORTH);
        mailClientPan.add(messageText, BorderLayout.CENTER);
        messageText.setBorder(BorderFactory.createTitledBorder("Текст сообщения"));
        
        companyPan.setLayout(new BorderLayout());
        companyPan.add(profilePanel, BorderLayout.NORTH);
        companyPan.add(scrollTable, BorderLayout.CENTER);
        companyPan.add(profileBtnPanel, BorderLayout.SOUTH);
        
    }
    
    private void setComponentNames() {
        companyTable.setName("companyTable");
        companyTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting())
                    GlobalListener.getInstance().actionPerformed(new ActionEvent(companyTable,
                                                                                 ActionEvent.ACTION_PERFORMED, ""));
            }
        });
        
        devbyImport.setName("loadFromDevBy");
        devbyImport.addActionListener(GlobalListener.getInstance());
        
        addCompany.setName("addCompany");
        editCompany.setName("editCompany");
        deleteCompany.setName("deleteCompany");
        addCompany.addActionListener(GlobalListener.getInstance());
        editCompany.addActionListener(GlobalListener.getInstance());
        deleteCompany.addActionListener(GlobalListener.getInstance());
        
        sendMessage.setName("sendMessage");
        sendMessage.addActionListener(GlobalListener.getInstance());
        selectFile.setName("selectFile");
        selectFile.addActionListener(GlobalListener.getInstance());
    }
    
    public JTable getCompanyTable() {
        return companyTable;
    }
    
    public void setNameField(String value) {
        this.nameField.setText(value);
    }

    public void setEmailField(String value) {
        this.emailField.setText(value);        
        if (companyTable.getSelectedRowCount() == 1)
            this.toField.setText(value);
        else this.toField.setText("*");
    }

    public void setFaceField(String value) {
        this.faceField.setText(value);
    }

    public void setPhoneField(String value) {
        this.phoneField.setText(value);
    }

    public void setSiteField(String value) {
        this.siteField.setText(value);
    }
    
    public void addMailAccount(Object mailAccount) {
        fromField.addItem(mailAccount);
    }
    
    public Object getSelectedMailAccount() {
        return fromField.getSelectedItem();
    }
    
    public void setAttachmentFilePath(String path) {
        attachPathField.setText(path);
    }
    
    public String getEMailTitle() {
        return titleField.getText().trim();
    }
    
    public String getEmailRecipients() {
        return toField.getText().trim();
    }
    
    public String getEMailMessage() {
        return messageText.getText().trim();
    }
    
    public String getAttachedFilePath() {
        return attachPathField.getText().trim();
    }
}


