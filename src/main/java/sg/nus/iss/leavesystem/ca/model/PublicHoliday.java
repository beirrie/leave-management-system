package sg.nus.iss.leavesystem.ca.model;

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
	
	private LocalDateTime dateOfHoliday; //
		
	private String description; //
	
	public PublicHoliday() {}
	
	public PublicHoliday(String name, LocalDateTime dt, String description) {
		this.publicHolidayName = name;
		this.dateOfHoliday = dt;
		this.description = description;
	}

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

	public void setDateOfHoliday(LocalDateTime dateTime){
		this.dateOfHoliday = dateTime;
	}
	public LocalDateTime getDateOfHoliday() {
		return dateOfHoliday;
	}

	public void setStartDate(LocalDateTime dateOfHoliday) {
		this.dateOfHoliday = dateOfHoliday;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
