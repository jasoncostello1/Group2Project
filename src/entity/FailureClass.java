package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries( {
	@NamedQuery(name = "FailureClass.findAll", query = "SELECT o FROM FailureClass o"),
	@NamedQuery(name = "FailureClass.findFailureClassByFailureClass", query = "SELECT o FROM FailureClass o WHERE o.failureClass=:failureClass"),
})

@Entity
public class FailureClass {
	@Id
	@Column(name="Failure_Class")
	private int failureClass;
	@Column(name="Description")
	private String desc;

	public int getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(int failureClass) {
		this.failureClass = failureClass;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public FailureClass(){
		
	}
	
	public FailureClass(int failureClass, String desc){
		this.failureClass = failureClass;
		this.desc = desc;
	}
	
	public void print() {
		if(desc == null)
			System.out.println("No Failure Class found!");
		else{
			System.out.println("Failure Class: " + failureClass + "\nDescription: " + desc);
		}
	}
}
