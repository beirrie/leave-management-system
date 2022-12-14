package sg.nus.iss.leavesystem.ca.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="users")

public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	public Long id;
	
	public String userName;
	public String password;
	
	@OneToOne(mappedBy="user")
	public Staff employee;
	
	public List<Role> roleSet;
	
	public User() {
		
	}
	
	public User(String username, String password) {
		this.userName = username;
		this.password = password;
	}
}
