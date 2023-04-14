package sia.hackathon.idea.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "FlightBooking")
public class FlightBooking {
	
	@Id
	private String bookingRefNo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="flightBooking")
	@JsonManagedReference
	private List<FlightTravelInfo> flightTravelInfos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="flightBooking")
	@JsonManagedReference
	private List<FlightPersonInfo> flightPersonInfos;
}
