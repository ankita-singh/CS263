package sportify.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;

@XmlRootElement
public class EventInstance {
	long ID;
	String activityId;
	long eventId;
	String participantId;
	Date eventDate;


	public EventInstance()
	{
		
	}

	public long getID() {
		return ID;
	}
	public void setID(long id) {
		ID = id;
	}
	
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getParticipantId() {
		return participantId;
	}
	
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
	
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

}
