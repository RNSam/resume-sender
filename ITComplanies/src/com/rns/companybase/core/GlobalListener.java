package com.rns.companybase.core;

import com.rns.companybase.view.AbstractView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import com.rns.companybase.core.ViewManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GlobalListener implements ActionListener, WindowListener {
	private static GlobalListener instance = new GlobalListener();

    private GlobalListener() {};
    
    public static GlobalListener getInstance() {
        return instance;
    }

    public AbstractView getView(JComponent aSource) {
    	JComponent jk = (JComponent) aSource.getParent();
    	while (!(jk.getParent() instanceof JFrame)) jk = (JComponent) jk.getParent();
		return (AbstractView)jk.getParent();    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String methodName = ((JComponent)e.getSource()).getName();
        
        AbstractView av = getView((JComponent)e.getSource());                              	     
        Object ic = ViewManager.getInstance().getControllerByView(av);
        invokeMethod(ic, methodName, null, null);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        Object ic = ViewManager.getInstance().getControllerByView((AbstractView) e.getSource());
        invokeMethod(ic, "windowOpened", null, null);
    }

    private Object invokeMethod(Object object, String methodName, Class<?>[] paramsTypes, Object arg) {
        Object result = null;
        try {
            Class targetObjectClass = object.getClass();
            Method method = findMethodAtClass(targetObjectClass, paramsTypes, methodName);
            if (arg != null)
                result = method.invoke(object, arg);
            else
                result = method.invoke(object);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private Method findMethodAtClass(final Class<?> type, Class<?>[] paramsTypes,
                                     final String methodName) throws NoSuchMethodException {

        Class<?> currentType = type;
        Method[] methods = type.getMethods();
        while (currentType != null) {
            for (Method buff : methods) {
                if (buff.getName().equals(methodName)) {
                    if (paramsTypes != null) {
                        Class<?>[] params = buff.getParameterTypes();
                        if (Arrays.deepEquals(paramsTypes, params))
                            return buff;
                    } else if (buff.getParameterTypes().length == 0)
                        return buff;
                }
            }
            currentType = currentType.getSuperclass();
        }
        throw new NoSuchMethodException();
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
