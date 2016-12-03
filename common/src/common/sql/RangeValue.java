package common.sql;

import java.io.Serializable;

public class RangeValue implements Serializable, ISQLValue {
	private Object lowValue;
	private Object highValue;
	
	public RangeValue(Object aLowValue, Object aHighValue) {
		lowValue = aLowValue;
		highValue = aHighValue;
	}
	
	public String getSQLValue() {
		return " between ".concat((String)lowValue).concat(" and ").concat((String)highValue);
	}
	
	public void setLowValue(Object lowValue) {
		this.lowValue = lowValue;
	}

	public void setHighValue(Object highValue) {
		this.highValue = highValue;
	}
			

}
