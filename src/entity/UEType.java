package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries( {
	@NamedQuery(name = "UEType.findAll", query = "SELECT o FROM UEType o"),
	@NamedQuery(name = "UEType.findUETypeByTAC", query = "SELECT o FROM UEType o WHERE o.tac=:tac"),
})

@Entity
public class UEType {
	@Id
	@Column(name="TAC") //Called "TAC" in Excel sheet
	private int tac;
	@Column(name="Marketing_Name")
	private String mName;
	@Column(name="Manufacturer")
	private String manu;
	@Column(name="Access_Capability")
	private String access;
	@Column(name="Model")
	private String model;
	@Column(name="Vendor_Name")
	private String vName;
	@Column(name="UE_Type")
	private String ueType;
	@Column(name="OS")
	private String os;
	@Column(name="Input_Mode")
	private String inputMode;

	public int getTac() {
		return tac;
	}

	public void setTac(int tac) {
		this.tac = tac;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getManu() {
		return manu;
	}

	public void setManu(String manu) {
		this.manu = manu;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	public String getUeType() {
		return ueType;
	}

	public void setUeType(String ueType) {
		this.ueType = ueType;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getInputMode() {
		return inputMode;
	}

	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}

	public UEType(){
		
	}
	
	public UEType(int tac, String mName, String manu, String access, String model,
				  String vName, String ueType, String os, String inputMode){
		super();
		this.tac = tac;
		this.mName = mName;
		this.manu = manu;
		this.access = access;
		this.model = model;
		this.vName = vName;
		this.ueType = ueType;
		this.os = os;
		this.inputMode = inputMode;
	}
	
	public void print() {
		if(access == null)
			System.out.println("No UE Type found!");
		else{
			System.out.println("UE Type: " + tac + "\tMarketing Name: " + mName + 
							   "\nManufacturer: " + manu + "\tAccess Capability: " + access + 
							   "\nModel: " + model + ", Vendor Name: " + vName + ", UE Type: " + ueType + 
							   "\nOS: " + os + ", Input Mode: " + inputMode);
		}
	}
}
