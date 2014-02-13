package compositeKeys;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class MCCMNCCompKey implements Serializable{
	@Column(name="MCC")
	private int mcc;
	@Column(name="MNC")	//"Operator" in main sheet
	private int mnc;
	
	public int getMcc() {
		return mcc;
	}
	public void setMcc(int mcc) {
		this.mcc = mcc;
	}
	public int getMnc() {
		return mnc;
	}
	public void setMnc(int mnc) {
		this.mnc = mnc;
	}
}
