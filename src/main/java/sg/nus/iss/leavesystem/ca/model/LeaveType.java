package sg.nus.iss.leavesystem.ca.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="leave_types")
public class LeaveType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
	public String leaveTypeName;

	@OneToMany(fetch= FetchType.LAZY,mappedBy = "typeOfLeave")
	public List<LeaveApplication> leaveApplications = new ArrayList<>();
	
	public LeaveType(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public LeaveType() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}
}
