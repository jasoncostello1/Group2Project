package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import compositeKeys.MCCMNCCompKey;

@NamedQueries( {
	@NamedQuery(name = "MCC_MNC.findAll", query = "SELECT o FROM MCC_MNC o"),
	@NamedQuery(name = "MCC_MNC.findbyMCC", query = "SELECT o FROM MCC_MNC o WHERE o.mcc=:mcc"),
	@NamedQuery(name = "MCC_MNC.findbyMNC", query = "SELECT o FROM MCC_MNC o WHERE o.mnc=:mnc"),
})

@Entity
@IdClass(MCCMNCCompKey.class)
public class MCC_MNC {
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name="ID")
//	private int id;
	@Id
	private int mcc;
	@Id
	private int mnc;
	@Column(name="Country")
	private String country;
	@Column(name="Operator") //TODO need to match this string to the Market/Operator values in ms
	private String operator;

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public MCC_MNC(){
		
	}
	
	public MCC_MNC(int mcc, int mnc, String country, String operator){
		this.mcc = mcc;
		this.mnc = mnc;
		this.country = country;
		this.operator = operator;
	}
	
	public void print() {
		if(operator == null)
			System.out.println("No MCC_MNC found!");
		else
			System.out.println("MCC: " + mcc + "\tMNC: " + mnc + "\nCountry: " + country + "\tOperator: " + operator);
	}
}
