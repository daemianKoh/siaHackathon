package sia.hackathon.idea.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Choice {

	private int index;
	private String finish_reason;
	
	@JsonProperty("message")
	private Message message;
}
