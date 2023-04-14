package sia.hackathon.idea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EntityScan("sia.hackathon.idea.model")
public class IdeaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdeaApplication.class, args);
	}

}
