package sia.hackathon.idea.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GptOutput {

	private String id;
	private String object;
	private String created;
	private String model;
	private List<Choice>choices;
	private Usage usage;
	
	@Override
	public String toString() {
		return "GptOutput [id=" + id + ", object=" + object + ", created=" + created + ", model=" + model + ", choices="
				+ choices + ", usage=" + usage + "]";
	}
	
}
