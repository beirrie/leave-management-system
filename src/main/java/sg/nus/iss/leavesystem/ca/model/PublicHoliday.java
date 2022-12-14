package sg.nus.iss.leavesystem.ca.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="public_holidays")
public class PublicHoliday {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id; //
	
	public String publicHolidayName; //
	
	public LocalDateTime startDate; //
	
	public LocalDateTime endDate; //
	
	public String description; //
	
	public PublicHoliday() {}
}
