package sia.hackathon.idea.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class FlightPersonInfo {

	@Id
	private Long id;
	
	private String bookingRefNo;
	
	private String personName;
	
	private String email;
	
	private String nationality;
	
	private String visa;
	
	private String baggage;
	
	private String pcr;
	
	private String vaccination;
}
