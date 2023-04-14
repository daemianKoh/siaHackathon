package sia.hackathon.idea.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Usage {
	
	private int completion_tokens;
	private int prompt_tokens;
	private int total_tokens;
}
