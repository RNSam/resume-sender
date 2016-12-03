/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rns.companybase.core;


import com.rns.companybase.controller.AbstractController;
import com.rns.companybase.controller.ModelChangeListener;
import com.rns.companybase.view.AbstractView;

import common.utils.PropertyFile;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author R.Samoylenko
 */
public class ViewManager {
    private final String VIEW_MAP_FILE = "view";
    private Map<AbstractView, AbstractController> ViewController = new HashMap<AbstractView, AbstractController>();
    private Map<AbstractController, AbstractView> ControllerView = new HashMap<AbstractController, AbstractView>();
    
    private static ViewManager instance = new ViewManager();
    private ViewManager() {};
    
    public static ViewManager getInstance() { return instance; }
        
    
    public void openView(String viewClass, Object initObject) {        
        Class cView = null;
        Class cController = null;
        AbstractController controller = null;
        AbstractView view = null;
        
        try {            
            cView = Class.forName(viewClass);                                                          
        } catch (ClassNotFoundException ex) {  Logger.getLogger(ViewManager.class.getName()).log(Level.SEVERE, "Ошибка загрузки представдения", ex); return ;}
        
        try {
            cController = Class.forName((String)PropertyFile.getProperty(VIEW_MAP_FILE, viewClass));
        } catch (ClassNotFoundException ex) { Logger.getLogger(ViewManager.class.getName()).log(Level.SEVERE, "Ошибка загрузки контроллера", ex); return ;}
               
        try {
            Constructor controllerConstructor;
            
            view = (AbstractView)cView.newInstance();
            
            if (initObject == null) {            
                controllerConstructor = cController.getConstructor();
                controller = (AbstractController) controllerConstructor.newInstance();                
            } else {
                controllerConstructor = cController.getConstructor(initObject.getClass());
                controller = (AbstractController) controllerConstructor.newInstance(initObject);
               
            }  
        } catch (NoSuchMethodException ex)
                { Logger.getLogger(ViewManager.class.getName()).log(Level.SEVERE, "Ошибка создания экземпляра контроллера", ex); return ;} 
          catch (InstantiationException e) {}
          catch (IllegalAccessException e) {}
          catch (InvocationTargetException e) {}
        

        ViewController.put(view, controller);
        ControllerView.put(controller, view);
        
        controller.initView();
    }
    
    public AbstractController getControllerByView(AbstractView view) {
        return ViewController.get(view);
    }
    
    public AbstractView getViewByController(AbstractController controler) {
        return ControllerView.get(controler);
    }
    
    public void updateViews(Object aModel) {
       Set keys = ViewController.keySet();
       for(Object key : keys) {
           if (ViewController.get(key) instanceof ModelChangeListener) {
                ModelChangeListener mcl = (ModelChangeListener)ViewController.get(key);
                mcl.modelHasChanged();
           }
       }
    }
}
