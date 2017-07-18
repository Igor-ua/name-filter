package org.task.api;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.task.core.Contact;
import org.task.core.ContactRepository;
import org.task.core.View;

/**
 * Root API
 */
@RestController
@RequestMapping("/")
public class RootApi {

	private static final String HELLO_API = "Hello API";
	private static final String REDIRECT_URL = "http://localhost:8586/hello";

	@Autowired
	private ContactRepository contactsRepo;

	@RequestMapping("/")
	public RedirectView index() {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(REDIRECT_URL);
		return redirectView;
	}

	@RequestMapping("/hello")
	public String hello() {
		return HELLO_API;
	}

	@RequestMapping(
			value = "/hello/{id}",
			method = RequestMethod.GET)
	@ResponseBody
	@JsonView(View.Summary.class)
	public Contact findOne(@PathVariable("id") long id) {
		return contactsRepo.findOne(id);
	}
}
