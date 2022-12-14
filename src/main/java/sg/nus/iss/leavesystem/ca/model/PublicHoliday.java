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
	private Long id; //
	
	private String publicHolidayName; //
	
	private LocalDateTime startDate; //
	
	private LocalDateTime endDate; //
	
	private String description; //
	
	private PublicHoliday() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPublicHolidayName() {
		return publicHolidayName;
	}

	public void setPublicHolidayName(String publicHolidayName) {
		this.publicHolidayName = publicHolidayName;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
