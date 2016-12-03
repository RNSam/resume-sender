/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.process;

/**
 *
 * @author R.Samoylenko
 */
public interface ProgressProcess {    
    public String getStartMessage();
    public String getStopMessage();
    public String getMessageTemlate();
    public int getTotal();
    public void execute(int i);    
}
