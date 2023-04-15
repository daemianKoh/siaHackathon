package sia.hackathon.idea.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class BookingSummaryResponse {
	
	private String bookingRefNo;
	private int numberOfPerson;
	private Date departureFromSgDate;
	private Date returnToSgDate;
	private String originFullName;
	private String destinationFullName;
	List<PersonInfo> personInfos;
}
