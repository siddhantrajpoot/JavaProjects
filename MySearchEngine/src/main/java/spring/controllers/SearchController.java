package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SearchController {

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@RequestMapping("/dosearch")
	public RedirectView doSearch(@RequestParam("inbox") String query) {

		RedirectView redirectView = new RedirectView();

		if (query.equals("")) {
			redirectView.setUrl("home");
			return redirectView;
		} else {

			String url = "https://www.google.com/search?q=" + query;
			redirectView.setUrl(url);
			return redirectView;
		}

	}

}
