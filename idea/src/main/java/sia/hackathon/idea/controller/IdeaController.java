package sia.hackathon.idea.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sia.hackathon.idea.model.FlightBooking;
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
	
	@GetMapping("/testRepo")
    public List<FlightBooking> testRepo() throws IOException {
        return ideaService.testRepo();
    }
	
	@RequestMapping(value = "/testUI", method = RequestMethod.GET)
	public ModelAndView detail(String applRefNo, ModelMap map) throws Exception {
		return new ModelAndView("fragments/index");
	}
}
