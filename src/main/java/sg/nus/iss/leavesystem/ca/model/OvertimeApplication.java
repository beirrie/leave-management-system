package sg.nus.iss.leavesystem.ca.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OvertimeApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id; //
	
	
	@ManyToOne
	@JoinColumn(name = "staff_id")
	public Staff staff;
	
	
	public LocalDateTime startDateTime; //
	public LocalDateTime endDateTime; //
	
	public String employeeComment; //
	
	public LocalDateTime appliedDateTime; //
	
	
	public String approvalStatus; //
	
	public String managerRemarks;//
	public LocalDate dateApplicationReviewed; //
	
	
	
}

