package sg.nus.iss.leavesystem.ca.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity

@Table(name = "users")

public class User {

	// Attribute
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userName;
	private String password;

	@OneToOne(mappedBy = "user")
	private Staff employee;

	@ManyToMany
	@JsonBackReference
	private List<Role> roles = new ArrayList<>();

	private Boolean isActive = true;

	// Constructor
	public User() {
	}

	public User(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	// Method

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Staff getEmployee() {
		return employee;
	}
	
	public void setEmployee(Staff employee) {
		this.employee = employee;
	}

	public List<Role> getRoleSet() {
		return roles;
	}

	public void setRoleSet(List<Role> roleSet) {
		this.roles = roleSet;
	}

	public void addRole(Role role) {
		roles.add(role);
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
}
