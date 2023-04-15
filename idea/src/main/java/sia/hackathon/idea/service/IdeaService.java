package sia.hackathon.idea.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sia.hackathon.idea.dao.repository.CountryRepository;
import sia.hackathon.idea.dao.repository.FlightBookingRepository;
import sia.hackathon.idea.dto.BookingSummaryResponse;
import sia.hackathon.idea.dto.Message;
import sia.hackathon.idea.dto.PersonInfo;
import sia.hackathon.idea.dto.RequestWrapper;
import sia.hackathon.idea.dto.Response;
import sia.hackathon.idea.model.Country;
import sia.hackathon.idea.model.FlightBooking;
import sia.hackathon.idea.model.FlightPersonInfo;
import sia.hackathon.idea.model.FlightTravelInfo;

@Service
public class IdeaService {

	static final String SINGAPORE_CD = "SIN";

	@Autowired
	FlightBookingRepository flightBookingRepo;
	
	@Autowired
	CountryRepository countryRepository;
	
	private List<PersonInfo> populatePersonInfo(List<FlightPersonInfo> flightPersonInfos){
		
		List<PersonInfo> personInfos = new ArrayList<>();
		
		for(FlightPersonInfo flightPersonInfo : flightPersonInfos) {
			PersonInfo p = new PersonInfo();
			p.setName(flightPersonInfo.getPersonName());
			p.setEmail(flightPersonInfo.getEmail());
			p.setNationality(flightPersonInfo.getNationality());
			p.setVisa(flightPersonInfo.getVisa());
			p.setBaggage(flightPersonInfo.getBaggage());
			p.setPcr(flightPersonInfo.getPcr());
			p.setVaccination(flightPersonInfo.getVaccination());
			p.setSeat(flightPersonInfo.getSeat());
			p.setPassport(flightPersonInfo.getPassport());
			p.setMeal(flightPersonInfo.getMeal());
			personInfos.add(p);
		}
		return personInfos;
	}
	
	public Response getBookingDetail(String bookingRef) {
		
		Response response = new Response();
		Optional<FlightBooking> optionalFlightBooking = flightBookingRepo.findById(bookingRef);
		
		if(optionalFlightBooking.isPresent()) {
			FlightBooking flightBooking = optionalFlightBooking.get();
			BookingSummaryResponse bookingSummary = new BookingSummaryResponse();
			List<FlightPersonInfo> flightPersonInfos = flightBooking.getFlightPersonInfos();
			List<FlightTravelInfo> flightTravelInfos = flightBooking.getFlightTravelInfos();
			
			bookingSummary.setBookingRefNo(bookingRef);
			bookingSummary.setNumberOfPerson(flightPersonInfos.size());
			
			List<PersonInfo> personInfos = populatePersonInfo(flightPersonInfos);
			bookingSummary.setPersonInfos(personInfos);
			
			for(FlightTravelInfo travelInfo : flightTravelInfos) {
				if(SINGAPORE_CD.equals(travelInfo.getOrigin())) {
					bookingSummary.setDepartureFromSgDate(travelInfo.getDepartDate());
					
					Country c = countryRepository.findFirstByCityCode(travelInfo.getOrigin());
					bookingSummary.setOriginFullName(c.getCity());
					c = countryRepository.findFirstByCityCode(travelInfo.getDestination());
					bookingSummary.setDestinationFullName(c.getCity());
				}
				else {
					bookingSummary.setReturnToSgDate(travelInfo.getArrivalDate());
				}
			}
			response.setO(bookingSummary);
		}
		else {
			response.setReturnCode(1);
		}
		return response;
	}
	
	public List<FlightBooking> testRepo() {
		
		return flightBookingRepo.findAll();
	}

	public void testGPT() {

		RequestWrapper wrapper = new RequestWrapper();
		Message m = new Message("system", "hello");
		List<Message> messages = new ArrayList<>();
		messages.add(m);
		wrapper.setMessages(messages);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("api-key", System.getenv("SIA_GPT_API_KEY"));

		HttpEntity<?> httpEntity = new HttpEntity<>(wrapper, headers);

		String urlTemplate = UriComponentsBuilder.fromHttpUrl(System.getenv("SIA_GPT_URL")).queryParam("api-version", "2023-03-15-preview")
				.encode().toUriString();

		System.out.println("urlTemplate >> " + urlTemplate);
		ResponseEntity<?> response = restTemplate.exchange(urlTemplate, HttpMethod.POST, httpEntity, String.class);

		System.out.println("STATUS >> " + response.getStatusCodeValue());
		System.out.println("object >> " + response.getBody());
	}
}
