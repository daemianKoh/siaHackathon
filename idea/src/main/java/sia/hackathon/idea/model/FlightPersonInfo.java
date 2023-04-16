package sia.hackathon.idea.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "FlightPersonInfo")
public class FlightPersonInfo {

	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="bookingRefNo")
	@JsonBackReference
	private FlightBooking flightBooking;
	
	private String personName;
	
	private String email;
	
	private String nationality;
	
	private String visa;
	
	private String pcr;
	
	private String vaccination;
	
	private String passport;
}
