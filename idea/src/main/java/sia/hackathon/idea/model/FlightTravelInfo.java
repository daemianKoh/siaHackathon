package sia.hackathon.idea.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class FlightTravelInfo {
	
	@Id
	private Long id;
	
	private String bookingRefNo;
	
	private String origin;
	
	private String destination;
	
	private Date departDate;
	
	private String departTime;
	
	private Date arrivalDate;
	
	private String arrivalTime;
}
