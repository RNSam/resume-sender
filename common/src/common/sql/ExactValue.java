package common.sql;

import java.io.Serializable;

public class ExactValue implements Serializable, ISQLValue{
	private Object value;
		
	public String getSQLValue() {
		return " = ".concat("'"+(String)value+"'");
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ExactValue(Object aValue) {
		value = aValue;
	}
	
}
