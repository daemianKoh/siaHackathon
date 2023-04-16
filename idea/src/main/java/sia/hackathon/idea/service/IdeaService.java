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
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sia.hackathon.idea.dao.repository.CountryRepository;
import sia.hackathon.idea.dao.repository.FlightBookingRepository;
import sia.hackathon.idea.dto.BookingSummaryResponse;
import sia.hackathon.idea.dto.GptOutput;
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
	
	private String initConversationBaseLine(String bookingRefNo, String name) {
		StringBuilder sb = new StringBuilder("Flight Booking Reference no: " + bookingRefNo + ".");
		sb.append(System.lineSeparator());
		
		Response response = getBookingDetail(bookingRefNo, name);
		BookingSummaryResponse bookingSummary = (BookingSummaryResponse)response.getO();
		
		if(bookingSummary.getNumberOfPerson() == 1) {
			sb.append(" I'm travelling alone.");
		}
		else {
			sb.append(" I'm travelling in a group of " + bookingSummary.getNumberOfPerson() + ".");
		}
		sb.append(System.lineSeparator());
		
		sb.append("Departing from " + bookingSummary.getOriginFullName() + " Destination at " + bookingSummary.getDestinationFullName());
		sb.append(System.lineSeparator());
		
		sb.append("Travel date is from " + bookingSummary.getDepartureFromSgDate() + " and return on " + bookingSummary.getReturnToSgDate());
		sb.append(System.lineSeparator());
		
		getProfileForChatGpt(bookingSummary.getPersonInfos());
		
		return sb.toString();
	}
	
	private String getStatus(PersonInfo p) {
		StringBuilder sb = new StringBuilder();
		
		if(p.getPassport().equalsIgnoreCase("N")) {
			sb.append("- passport not pack");
			sb.append(System.lineSeparator());
		}
		if(p.getPcr().equalsIgnoreCase("N")) {
			sb.append("- PCR not done");
			sb.append(System.lineSeparator());
		}
		if(p.getVaccination().equalsIgnoreCase("N")) {
			sb.append("- Covid Vaccination not done");
			sb.append(System.lineSeparator());
		}
		if(p.getVisa().equalsIgnoreCase("N")) {
			sb.append("- not apply");
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	private String getProfileForChatGpt(List<PersonInfo> personInfos) {
		StringBuilder sb = new StringBuilder();
		
		if(personInfos.size() == 1) {
			sb.append("This are my status currenty: ");
			sb.append(System.lineSeparator());
			sb.append(getStatus(personInfos.get(0)));
		}
		else {
			sb.append("Below are the status for the following people: ");
			sb.append(System.lineSeparator());
			for(PersonInfo p : personInfos) {
				sb.append("Name: " + p.getName());
				sb.append(System.lineSeparator());
				sb.append(getStatus(p));
			}
		}
		return sb.toString();
	}
	
	private List<PersonInfo> populatePersonInfo(List<FlightPersonInfo> flightPersonInfos, String name){
		
		List<PersonInfo> personInfos = new ArrayList<>();
		
		for(FlightPersonInfo flightPersonInfo : flightPersonInfos) {
			
			if(!ObjectUtils.isEmpty(name)) {
				if(name.equalsIgnoreCase(flightPersonInfo.getPersonName())) {
					PersonInfo p = new PersonInfo();
					p.setName(flightPersonInfo.getPersonName());
					p.setEmail(flightPersonInfo.getEmail());
					p.setNationality(flightPersonInfo.getNationality());
					p.setVisa(flightPersonInfo.getVisa());
					p.setPcr(flightPersonInfo.getPcr());
					p.setVaccination(flightPersonInfo.getVaccination());
					p.setPassport(flightPersonInfo.getPassport());
					personInfos.add(p);
					break;
				}
			}
			else {
				PersonInfo p = new PersonInfo();
				p.setName(flightPersonInfo.getPersonName());
				p.setEmail(flightPersonInfo.getEmail());
				p.setNationality(flightPersonInfo.getNationality());
				p.setVisa(flightPersonInfo.getVisa());
				p.setPcr(flightPersonInfo.getPcr());
				p.setVaccination(flightPersonInfo.getVaccination());
				p.setPassport(flightPersonInfo.getPassport());
				personInfos.add(p);
			}
		}
		return personInfos;
	}
	
	public Response getBookingDetail(String bookingRef, String name) {
		
		Response response = new Response();
		Optional<FlightBooking> optionalFlightBooking = flightBookingRepo.findById(bookingRef);
		
		if(optionalFlightBooking.isPresent()) {
			FlightBooking flightBooking = optionalFlightBooking.get();
			BookingSummaryResponse bookingSummary = new BookingSummaryResponse();
			List<FlightPersonInfo> flightPersonInfos = flightBooking.getFlightPersonInfos();
			List<FlightTravelInfo> flightTravelInfos = flightBooking.getFlightTravelInfos();
			
			bookingSummary.setBookingRefNo(bookingRef);
			bookingSummary.setNumberOfPerson(flightPersonInfos.size());
			
			List<PersonInfo> personInfos = populatePersonInfo(flightPersonInfos, name);
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
		ResponseEntity<?> response = restTemplate.exchange(urlTemplate, HttpMethod.POST, httpEntity, GptOutput.class);

		GptOutput output = (GptOutput)response.getBody();
		System.out.println("output >> " + output);
		System.out.println("output.getChoices() >> " + output.getChoices());
		System.out.println("output >> " + output.getChoices().get(0).getMessage());
		System.out.println("STATUS >> " + response.getStatusCodeValue());
		System.out.println("object >> " + response.getBody());
	}
}
