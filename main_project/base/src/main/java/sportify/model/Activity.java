package sportify.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;

import java.io.Serializable;

@XmlRootElement
public class Activity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name;
	String description;
	String ownerId;

	public Activity(){
		
	}

	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOwnerId() {
		return ownerId;
	}
	@XmlElement
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

}
