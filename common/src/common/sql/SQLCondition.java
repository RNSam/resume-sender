package common.sql;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

public class SQLCondition implements Serializable{
    private Class viewClass;
    Map<Object, Object> conditions = new HashMap<Object, Object>();
    
    public Map<Object, Object> getConditions() {
		return conditions;
	}

	public SQLCondition() {super();}
    
    public SQLCondition(Class aViewClass) {
        viewClass = aViewClass;
    }
    
    public void addCondition(String fieldName, Object value) {
        conditions.put(fieldName, value);
    }      

    public Class getViewClass() {
        return viewClass;
    }
}
