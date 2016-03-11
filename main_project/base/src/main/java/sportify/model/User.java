package sportify.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class User{


		private String fname;
		private String lname;
		private String address;
		private String username;
		private String email;
		private String phone;
		private long id;


		public User(){

		}

		public User(String fname,String lname, String phone, String address, String username, String email, long id) {
		this.setFname(fname);
		this.setLname(lname);
		this.setPhone(phone);
		this.setAddress(address);
		this.setUsername(username);
		this.setEmail(email);
		this.setId(id);

		}

		@XmlElement
		public String getFname() 
		{
			return fname;
		}

		public void setFname(String fname)
		{
			this.fname = fname;
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

		@XmlElement
		public String getPhone() 
		{
			return phone;
		}

		public void setPhone(String phone)
		{
			
			this.phone = phone;
		}

		@XmlElement
		public String getLname() 
		{
			return lname;
		}

		public void setLname(String lname)
		{
			this.lname = lname;
		}


}