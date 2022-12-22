package sg.nus.iss.leavesystem.ca.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class APIKey {
	
	@Id
	private String id;
	
	private String holderFirstName;
	
	private String holderLastName;
	
	private String emailAddress;
	
	private LocalDateTime dateTimeCreated;
	
	public APIKey() {
		
	}
	
	//Constructor
	public APIKey(String firstName, String lastName, String emailAdd) {
		//API key is hard coded for ease of demo AND illustration purposes only. 
		// Will switch to random UUID generation for real world usage. 
		this.id = "d2ad199e-0602-44e7-a83e-3c538a9de7c4";
//		this.id =  UUID.randomUUID().toString();
		this.holderFirstName = firstName;
		this.holderLastName = lastName;
		this.emailAddress = emailAdd;
		this.dateTimeCreated = LocalDateTime.now();
	}
	
	@Override
	public String toString() {
		return id;
	}
}
