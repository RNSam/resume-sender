package common.sql;

import java.io.Serializable;

public class LikeValue extends ExactValue implements Serializable {

	public LikeValue(Object aValue) {
		super(aValue);		
	}
	
	public String getSQLValue() {
		return " like ".concat(super.getSQLValue());		
	};

}
