package sia.hackathon.idea.model;

import java.util.Date;

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
@Table(name = "FlightTravelInfo")
public class FlightTravelInfo {
	
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="bookingRefNo")
	@JsonBackReference
	private FlightBooking flightBooking;
	
	private String origin;
	
	private String destination;
	
	private Date departDate;
	
	private String departTime;
	
	private Date arrivalDate;
	
	private String arrivalTime;
}
