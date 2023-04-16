package sia.hackathon.idea.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sia.hackathon.idea.dto.Response;
import sia.hackathon.idea.service.IdeaService;

@RestController
public class IdeaController {

	@Autowired
	IdeaService ideaService;

	@GetMapping("/")
	public String home() throws IOException {
		// ideaService.test();
		ideaService.testGPT();
		return "Hello World " + System.getenv("SIA_GPT_API_KEY");
	}

	@RequestMapping(value = "/summary", method = RequestMethod.GET)
	public ModelAndView summary(@RequestParam(name = "bookingRefNo") String bookingRefNo, Model model) throws Exception {
		model.addAttribute("bookingRefNo", bookingRefNo);
		return new ModelAndView("fragments/summary/summaryMain");
	}

	@RequestMapping(value = "/getBookingSummary", method = RequestMethod.POST)
	public Response getBookingSummary(@RequestParam(name = "bookingRefNo") String bookingRefNo, HttpSession session)
			throws Exception {
		session.setAttribute("bookingRefNo", bookingRefNo);
		return ideaService.getBookingDetail(bookingRefNo, null);
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public ModelAndView loadDetailPage(@RequestParam(name = "bookingRefNo") String bookingRefNo, @RequestParam(name = "nameValue") String nameValue,
			HttpSession session) throws Exception {
		
		session.setAttribute("nameValue", nameValue);
		return new ModelAndView("fragments/detail/detailMain");
	}
	
	@RequestMapping(value = "/getDetail", method = RequestMethod.POST)
	public Response getDetail(HttpSession session)
			throws Exception {
		
		String name = (String)session.getAttribute("nameValue");
		String bookRefNo = (String)session.getAttribute("bookingRefNo");		
		return ideaService.getBookingDetail(bookRefNo, name);
	}
	
	@RequestMapping(value = "/updateDetail", method = RequestMethod.POST)
	public ModelAndView updateDetail(@RequestParam(name = "tbPssport") String tbPssport, @RequestParam(name = "tbVisa") String tbVisa,
			@RequestParam(name = "tbPcr") String tbPcr, @RequestParam(name = "tbVax") String tbVax,
			HttpSession session) throws Exception {
		
		System.out.println("tbPssport " + tbPssport);
		System.out.println("tbVisa " + tbVisa);
		System.out.println("tbPcr " + tbPcr);
		System.out.println("tbVax " + tbVax);
		
		String name = (String)session.getAttribute("nameValue");
		String bookRefNo = (String)session.getAttribute("bookingRefNo");
		ideaService.updateDetail(tbPssport, tbVisa, tbPcr, tbVax, name, bookRefNo);
		
		return new ModelAndView("redirect:/summary?bookingRefNo="+bookRefNo);
	}
}
