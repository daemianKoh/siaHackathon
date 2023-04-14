package sia.hackathon.idea.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Usage {
	
	private int completion_tokens;
	private int prompt_tokens;
	private int total_tokens;
}
