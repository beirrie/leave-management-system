package sg.nus.iss.leavesystem.ca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="leave_types")
public class LeaveType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public String id;
	
	public String leaveTypeName;
	
	public LeaveType() {};
	
}
