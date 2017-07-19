package org.task.api;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.task.core.*;

import java.util.List;

/**
 * Root API
 */
@RestController
@RequestMapping("/")
public class RootApi {

	private static final String HELLO_API = "Hello API";
	private static final String REDIRECT_URL = "http://localhost:8586/hello";

	@Autowired
	private ContactsService contactsService;

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
			value = "/hello/contacts",
			params = {"nameFilter"},
			method = RequestMethod.GET)
	@ResponseBody
	@JsonView(View.Summary.class)
	public List findContacts(@RequestParam(value = "nameFilter") String nameFilter) {
		System.out.println("nameFilter: " + nameFilter);
		Page page = contactsService.getContacts("Mike", 1);
		return page.getResult();
	}

	@RequestMapping(
			value = "/hello/contacts",
			params = {"nameFilter", "page"},
			method = RequestMethod.GET)
	@ResponseBody
	@JsonView(View.Summary.class)
	public List findFilteredContacts(@RequestParam(value = "nameFilter") String regexp,
											  @RequestParam(value = "page") Integer pageNumber) {
		Page page = contactsService.getContacts(regexp, pageNumber);
		System.out.println(page);
		return page.getResult();
	}
}
