package common.dialog;

import common.process.ProgressProcess;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ProgressDialog {
    private MessageDialogInner messageDialog;
   
    private static final ProgressDialog instance = new ProgressDialog();        
    private ProgressDialog() { 
        super();                 
    }    
    public static ProgressDialog getInstance() { return instance; }
    
    public void startProcess(ProgressProcess process, Window aOwner) {                  
        new Thread(new MessageDialogInner(aOwner, process)).start();      
    }
    
    public void stopProcess() {  
        //messageDialog.closeDialog();  
    }
    
    
    private class MessageDialogInner extends JDialog implements Runnable {
        private final int DEF_WITH_SIZE = 500;
        private final int DEF_HIGH_SIZE = 40;
        private ProgressProcess process;
        
        private Container contentPane;
        JLabel message = new JLabel("", SwingConstants.CENTER); 
        
        public MessageDialogInner (Window aOwner, ProgressProcess process) {
            super(aOwner);
            this.process = process;
            setSize(DEF_WITH_SIZE, DEF_HIGH_SIZE);
            setLocationRelativeTo(null);
            setAlwaysOnTop(true);
            setAutoRequestFocus(true);
            setUndecorated(true);                          
            setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
            
            contentPane = getContentPane();
            contentPane.setLayout(new BorderLayout());            
            contentPane.add(message, BorderLayout.CENTER);           
        }                
        
        public void closeDialog() {
            setVisible(false);
        }                

        @Override
        public void run() {
            setVisible(true);                         
            message.setText(process.getStartMessage());
            sleep(500);
            int i=0;
            while (i < process.getTotal()) {
                message.setText(String.format(process.getMessageTemlate(), i+1,  process.getTotal()));
                process.execute(i++);                                
            }        
            message.setText(process.getStopMessage());                        
            sleep(2000);
            closeDialog();
        }
        
        private void sleep(int timeOut) {
            try {
                Thread.sleep(timeOut);
            } catch (InterruptedException ex) {}
        }
    }
    
}
