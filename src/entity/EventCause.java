package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import compositeKeys.CauseCodeEventIdCompKey;

@NamedQueries( {
	@NamedQuery(name = "EventCause.findAll", query = "SELECT o FROM EventCause o"),
	@NamedQuery(name = "EventCause.findByEventId", query = "SELECT o FROM EventCause o WHERE o.eventId=:eventId"),
	@NamedQuery(name = "EventCause.findByCauseCode", query = "SELECT o FROM EventCause o WHERE o.causeCode=:causeCode"),
})

@Entity
@IdClass(CauseCodeEventIdCompKey.class)
public class EventCause {
	@Id
	private int causeCode;
	@Id
	private int eventId;
	@Column(name="Description")
	private String desc;

	public int getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(int causeCode) {
		this.causeCode = causeCode;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public EventCause(){
		
	}
	
	public EventCause(int causeCode, int eventId, String desc){
		super();
		this.causeCode = causeCode;
		this.eventId = eventId;
		this.desc = desc;
	}

	public void print() {
		if(desc == null)
			System.out.println("No EventCause found!");
		else{
			System.out.println("EventID: " + eventId + "\tCause Code: " + causeCode +
							   "\nDescription: " + desc);
		}
	}
}

