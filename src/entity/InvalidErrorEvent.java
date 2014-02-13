package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries( {
	@NamedQuery(name = "InvalidErrorEvent.findAll", query = "SELECT o FROM InvalidErrorEvent o"),
	@NamedQuery(name = "InvalidErrorEvent.findInvalidErrorEventByEventId", query = "SELECT o FROM InvalidErrorEvent o WHERE o.eventId=:eventId"),
})

@Entity
public class InvalidErrorEvent {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="Date")
	private Date date;
	@Column(name="Event_ID")
	private int eventId;
	@Column(name="Failure_Class")
	private String failureClass;
	@Column(name="UE_Type")
	private int ueType;
	@Column(name="Market")
	private int market;
	@Column(name="Operator")
	private int operator;
	@Column(name="Cell_ID")
	private int cellId;
	@Column(name="Duration")
	private int duration;
	@Column(name="Cause_Code")
	private String causeCode;
	@Column(name="NE_Version")
	private String neVersion;
	@Column(name="IMSI")
	private long imsi;
	@Column(name="HIER3_ID")
	private long hier3_id;
	@Column(name="HIER32_ID")
	private long hier32_id;
	@Column(name="HIER321_ID")
	private long hier321_id;
	
	public InvalidErrorEvent(){
		
	}
	
	public InvalidErrorEvent(Date date, int eventId, String failureClass, int ueType, int market, int operator, int cellId, int duration,
						String causeCode, String neVersion, long imsi, long hier3_id, long hier32_id, long hier321_id){
		super();
		this.date = date;
		this.eventId = eventId;
		this.failureClass = failureClass;
		this.ueType = ueType;
		this.market = market;
		this.operator = operator;
		this.cellId = cellId;
		this.duration = duration;
		this.causeCode = causeCode;
		this.neVersion = neVersion;
		this.imsi = imsi;
		this.hier3_id = hier3_id;
		this.hier32_id = hier32_id;
		this.hier321_id = hier321_id;
	}

	public void print(){
		if(neVersion == null)
			System.out.println("No InvalidErrorEvent found!");
		else{
			System.out.println("Date: " + date + "\tEvent ID: " + eventId + "\t\tFailure Class: " + failureClass + 
							   "\nUE Type: " + ueType + "\t\tMarket: " + market + "\t\tOperator: " + operator + 
							   "\nCell ID: " + cellId + "\t\t\tDuration: " + duration + "\t\tCause Code: " + causeCode + 
							   "\nNE Version: " + neVersion + "\t\t\tIMSI: " + imsi + "\tHIER3_ID: " + hier3_id + 
							   "\nHIER32_ID: " + hier32_id + "\tHIER321_ID: " + hier321_id);
		}
	}
	
}
