package sia.hackathon.idea.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class FlightBooking {
	
	@Id
	private String bookingRefNo;
	
	@OneToMany(targetEntity=FlightTravelInfo.class, fetch = FetchType.LAZY, mappedBy="flightBooking")
	private List<FlightTravelInfo> flightTravelInfos;
	
	@OneToMany(targetEntity=FlightPersonInfo.class, fetch = FetchType.LAZY, mappedBy="flightBooking")
	private List<FlightPersonInfo> flightPersonInfos;
}
