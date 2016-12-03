package com.rns.companybase.core;


import common.sql.SQLCondition;

import common.to.ISingleEntity;

import servercmd.ServerDeleteCommand;
import servercmd.ServerInsertCommand;
import servercmd.ServerSelectCommand;
import servercmd.ServerUpdateCommand;


public class DataModel {
    private static DataModel instance = new DataModel();

    private DataModel() { };   
   
    public static DataModel getInstance() {
        return instance;
    }  
    
    public Object selectExecute(SQLCondition condition){
    	ServerSelectCommand sc = new ServerSelectCommand();
        return sc.execute(condition);        
    }
    
    public Object insertExecute(ISingleEntity viewObject) {
        ServerInsertCommand sc = new ServerInsertCommand();
        sc.execute(viewObject);
        ViewManager.getInstance().updateViews(null);
        return null;
    }
    
    public Object updateExecute(ISingleEntity viewObject){
        ServerUpdateCommand sc = new ServerUpdateCommand();
        sc.execute(viewObject);
        ViewManager.getInstance().updateViews(null);
        return null;
    }
    
    public Object deleteExecute(ISingleEntity viewObject){
        ServerDeleteCommand sc = new ServerDeleteCommand();
        sc.execute(viewObject);
        ViewManager.getInstance().updateViews(null);        
        return null;
    }
    
}
