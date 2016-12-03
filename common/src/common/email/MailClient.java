/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.email;

import common.utils.PropertyFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author R.Samoylenko
 */
public class MailClient {
    private Properties property;
    private Session session;
    private String host;
    private InternetAddress from;
    private String password;
    
    private MailClient() {}
    
    public void sendMessage(String[] aTo, String aTitle, String aText, String aFileName) {
        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            
            // Set From: header field of the header.
            message.setFrom(from);

              // Set To: header field of the header.
            for(String to : aTo)
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(aTitle);

            // Есть файл - создаем сообщение с несколькими частями (текст + сам файл)
            if (!aFileName.equals("")) {
                Multipart multipart = new MimeMultipart();
                
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(aText.replace("\n", "<br>"), "text/html;charset=\"UTF-8\"");
                multipart.addBodyPart(messageBodyPart);
                
                BodyPart fileBodyPart = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(aFileName);
                fileBodyPart.setDataHandler(new DataHandler(fds));
                Path p = Paths.get(aFileName);
                fileBodyPart.setFileName(p.getFileName().toString());
                
                multipart.addBodyPart(fileBodyPart);
                message.setContent(multipart);
            }
            else message.setContent(aText.replace("\n", "<br>"), "text/html;charset=\"UTF-8\"");            

            // Send message            
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from.getAddress(), password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();             
            System.out.println("Message TO:".concat(recipientsToString(aTo)).concat(" sent succesfuly"));
        }catch (MessagingException mex) {       
            System.out.println("Error! While sending message TO:".concat(recipientsToString(aTo)).concat(" - ").concat(mex.getMessage()));
        }
    }

    public MailClient(Properties property) throws AddressException {
        this.property = property;
        session = Session.getDefaultInstance(property);        
        from = new InternetAddress(property.getProperty("mail.smtp.user"));
        password = property.getProperty("mail.smtp.password");
        host = property.getProperty("mail.smtp.host");
    }
     
    public static void main(String[] args) throws AddressException {
        MailClient mailclient= new MailClient(PropertyFile.getProperties("yahoo"));
        String[] s = {"rnsamoylenko@yahoo.com"};
        mailclient.sendMessage(s, "Проба", "Некий текст", "d:\\anketa.pdf");
    }
    
    private String recipientsToString(String[] aTo) {
        StringBuffer sb = new StringBuffer();
        for(String to : aTo)
            sb.append(to);
        return sb.toString();
    }
    
}
