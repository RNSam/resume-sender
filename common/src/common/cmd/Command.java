package common.cmd;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Command extends Serializable, Remote {
    
    public Object execute(Object param) throws RemoteException;
}
