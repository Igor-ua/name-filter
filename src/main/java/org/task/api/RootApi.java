package org.task.api;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.task.core.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Root API
 */
@RestController
@RequestMapping("/")
public class RootApi {

	private static final String HELLO_API = "Hello API";

	@Autowired
	private ContactsService contactsService;

	@RequestMapping("/")
	public RedirectView index(HttpServletRequest request) {
		RedirectView redirectView = new RedirectView();
		String redirectUrl = request.getRequestURL().toString() + "hello";
		redirectView.setUrl(redirectUrl);
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
	public RedirectView findContacts(@RequestParam(value = "nameFilter") String nameFilter, HttpServletRequest request) {
		RedirectView redirectView = new RedirectView();
		String redirectUrl = request.getRequestURL().toString() + "?nameFilter=" + nameFilter + "&page=1";
		redirectView.setUrl(redirectUrl);
		return redirectView;
	}

	@RequestMapping(
			value = "/hello/contacts",
			params = {"nameFilter", "page"},
			method = RequestMethod.GET)
	@ResponseBody
	@JsonView(View.Summary.class)
	public List findFilteredContacts(@RequestParam(value = "nameFilter") String regexp,
									 @RequestParam(value = "page") Integer pageNumber,
									 HttpServletResponse response) {
		Page page = contactsService.getContacts(regexp, pageNumber);
		response.setHeader("X-Pagination-Page", Integer.toString(page.getPageNumber()));
		response.setHeader("X-Pagination-PageSize", Integer.toString(page.getPageSize()));
		response.setHeader("X-Pagination-HasMore", Boolean.toString(page.hasMore()));
		return page.getResult();
	}
}
