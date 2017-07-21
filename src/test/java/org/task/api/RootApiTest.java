package org.task.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.task.core.Contact;
import org.task.core.ContactsService;
import org.task.core.Page;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RootApi.class)
public class RootApiTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ContactsService contactsService;

	@Value("${page.size}")
	private Integer pageSize;
	private Page defaultPage;
	private static final String DEFAULT_REGEXP = "^.*[1-8].*$";

	@Before
	public void startup() {
		List<Contact> result = new ArrayList<>();
		Contact c1 = new Contact(9L);
		c1.setName("Mike_9");
		result.add(c1);
		defaultPage = new Page(result, 1, pageSize, false);
	}

	@Test
	public void testRootApiMessage() {
		try {
			this.mvc.perform(get("/").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk())
					.andExpect(content().string("Url to use: /hello/contacts?nameFilter=RegExp"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRedirect() {
		try {
			this.mvc.perform(get("/hello/contacts?nameFilter=^.*[1-8].*$")
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().is3xxRedirection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindFilteredContacts() {
		when(contactsService.getContacts(DEFAULT_REGEXP, 1)).thenReturn(defaultPage);
		try {
			this.mvc.perform(get("/hello/contacts?nameFilter=" + DEFAULT_REGEXP + "&page=1"))
					.andExpect(status().isOk())
					.andExpect(content().json("[{\"id\":9,\"name\":\"Mike_9\"}]"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testWrongRegexp() {
		String wrongRegexp = "^.*[].*$";
		try {
			this.mvc.perform(get("/hello/contacts?nameFilter=" + wrongRegexp + "&page=1"))
					.andExpect(status().is5xxServerError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testHeaders() {
		when(contactsService.getContacts(DEFAULT_REGEXP, 1)).thenReturn(defaultPage);
		try {
			this.mvc.perform(get("/hello/contacts?nameFilter=" + DEFAULT_REGEXP + "&page=1"))
					.andExpect(header().string("X-Pagination-Page", "1"))
					.andExpect(header().string("X-Pagination-PageSize", Integer.toString(pageSize)))
					.andExpect(header().string("X-Pagination-HasMore", Boolean.toString(false)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}