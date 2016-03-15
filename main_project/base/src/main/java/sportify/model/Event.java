package sportify.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.io.Serializable;


@XmlRootElement
public class Event implements Serializable{

	
	
	String id;
	String day;
	int s_hour;
	int s_min;
	int e_hour;
	int e_min;
	String activity;
	String owner;
	String ownerId;
	

	public Event(){

		}

	public String getId() {
		return id;
	}
	@XmlElement
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDay() {
		return day;
	}
	@XmlElement
	public void setDay(String day) {
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
	
	public String getActivity() {
		return activity;
	}
	@XmlElement
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getOwner() {
		return owner;
	}
	@XmlElement
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwnerId() {
		return ownerId;
	}
	@XmlElement
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
}	
