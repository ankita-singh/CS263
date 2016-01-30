package sportify.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class User{


		private String name;
		private String address;
		private String username;
		private String email;
		private long id;


		public User(){

		}

		public User(String name, String address, String username, String email, long id) {
		this.setName(name);
		this.setAddress(address);
		this.setUsername(username);
		this.setEmail(email);
		this.setId(id);

		}

		@XmlElement
		public String getName() 
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}
	
		@XmlElement
		public String getAddress()
		{
			return address;
		}

		public void setAddress(String address) 
		{
			this.address = address;
		}

		@XmlElement
		public String getUsername() 
		{
			return username;
		}

		public void setUsername(String username)
		{
			this.username = username;
		}

		@XmlElement
		public String getEmail() 
		{
			return email;
		}

		public void setEmail(String email)
		{
			this.email = email;
		}


	    @XmlElement
		public long getId() 
		{
			return id;
		}

		public void setId(long id)
		{
			this.id = id;
		}



}