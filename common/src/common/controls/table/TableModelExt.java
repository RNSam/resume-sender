package common.controls.table;


import common.to.IViewObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class TableModelExt implements TableModel {
    private ArrayList<IViewObject> VO;
    
    
    public TableModelExt(ArrayList aVO) {
        super();
        VO = aVO;
    }
    
    public void setViewObject(ArrayList aVO) {
        VO = aVO;
    }
    
    @Override
    public int getRowCount() {
        return VO.size();       
    }

    @Override
    public int getColumnCount() {       
        if (VO.size() == 0)
            return 0;
        else return VO.get(0).getFields().size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (VO.size() == 0)
            return null;
        else return (VO.get(0).getFields().get(columnIndex)).getCaption();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Object obj = getValueAt(0, columnIndex);
        if (obj == null)
            return String.class;
        else return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (VO.size() == 0)
            return null;
        else {            
            try {
                Class c;
                c = Class.forName(VO.get(rowIndex).getClass().getName());
                String mthodName = new String("get".concat(VO.get(rowIndex).getFields().get(columnIndex).getName()));
                Method m = c.getMethod(mthodName, null);
                return m.invoke(VO.get(rowIndex), (Object[])null);
            } catch (ClassNotFoundException e) {}
              catch (NoSuchMethodException e) {}
              catch (IllegalAccessException e) {}
              catch (InvocationTargetException e) {}            
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }
}
