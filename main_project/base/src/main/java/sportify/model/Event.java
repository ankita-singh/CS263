package sportify.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;


@XmlRootElement
public class Event {
	long event_id;
	int day;
	int s_hour;
	int s_min;
	int e_hour;
	int e_min;
	String activityId;
	String owner;
	

	public Event(){

		}
	
	public int getDay() {
		return day;
	}
	@XmlElement
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getS_hour() {
		return s_hour;
	}
	@XmlElement
	public void setS_hour(int s_hour) {
		this.s_hour = s_hour;
	}
	public int getS_min() {
		return s_min;
	}
	@XmlElement
	public void setS_min(int s_min) {
		this.s_min = s_min;
	}
	public int getE_hour() {
		return e_hour;
	}
	@XmlElement
	public void setE_hour(int e_hour) {
		this.e_hour = e_hour;
	}
	public int getE_min() {
		return e_min;
	}
	@XmlElement
	public void setE_min(int e_min) {
		this.e_min = e_min;
	}
	
	public long getEvent_id() {
		return event_id;
	}
	@XmlElement
	public void setEvent_id(long event_id) {
		event_id = event_id;
	}
	public String getActivityId() {
		return activityId;
	}
	@XmlElement
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getOwner() {
		return owner;
	}
	@XmlElement
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}	
