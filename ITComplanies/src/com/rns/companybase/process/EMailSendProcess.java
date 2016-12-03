/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rns.companybase.process;

import common.email.MailClient;
import common.process.ProgressProcess;
import common.utils.PropertyFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;

/**
 *
 * @author R.Samoylenko
 */
public class EMailSendProcess implements ProgressProcess {
    private static final String START_MESSAGE = "Отправка сообщений...";
    private static final String STOP_MESSAGE = "Отправка завершена";
    private static final String PROCESS_MESSAGE_TEMPLATE = "Отправка %d из %d ...";

    private String[] recipientList;    
    private String title;
    private String message;
    private String filePath;
    
    private int total;
    
    MailClient mc;    
    
    public EMailSendProcess(String[] recipients, String aSettingFileName, String aTitle, String aMessage, String aFilePath) {
        super();
        title = aTitle;
        message = aMessage;
        filePath = aFilePath;
        recipientList = recipients;
        total = recipients.length;        
        try {
           mc = new MailClient(PropertyFile.getProperties(aSettingFileName));
        } catch (AddressException ex) {  Logger.getLogger(EMailSendProcess.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    @Override
    public String getStartMessage() {
        return START_MESSAGE;
    }

    @Override
    public String getStopMessage() {
        return STOP_MESSAGE;
    }

    @Override
    public String getMessageTemlate() {
        return PROCESS_MESSAGE_TEMPLATE;
    }

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public void execute(int i) {
       String[] recipients = recipientList[i].split(",");                
       mc.sendMessage(recipients, title, message, filePath); 
    }
    
}
