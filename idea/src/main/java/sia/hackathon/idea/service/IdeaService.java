package sia.hackathon.idea.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sia.hackathon.idea.dto.Message;
import sia.hackathon.idea.dto.RequestWrapper;

@Service
public class IdeaService {



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
