package sia.hackathon.idea.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sia.hackathon.idea.service.IdeaService;


@RestController
public class IdeaController {

	@Autowired
	IdeaService ideaService;
	
	@GetMapping("/")
    public String home() throws IOException {
		//ideaService.test();
		ideaService.testGPT();
        return "Hello World " + System.getenv("SIA_GPT_API_KEY");
    }
}