package sia.hackathon.idea.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {
	
	private String role;
	private String content;
	
	public Message() {
		
	}
	
	public Message(String role, String content) {
		this.role = role;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message [role=" + role + ", content=" + content + "]";
	}
}
