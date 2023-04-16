package sia.hackathon.idea.dto;

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

	@Override
	public String toString() {
		return "Choice [index=" + index + ", finish_reason=" + finish_reason + ", message=" + message + "]";
	}
}
