package compositeKeys;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class CauseCodeEventIdCompKey implements Serializable {
	
	@Column(name="Cause_Code")
	private int causeCode;
	@Column(name="Event_ID")
	private int eventId;
	
	
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
}
