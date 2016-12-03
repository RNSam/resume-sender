/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.cmd;

import common.to.ISingleEntity;
import java.rmi.RemoteException;

/**
 *
 * @author R.Samoylenko
 */
public interface DMLCommand {
     public Object execute(ISingleEntity param) throws RemoteException;
}
