package org.task.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.task.core.ContactsService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RootApi.class)
public class RootApiTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ContactsService contactsService;

	@Test
	public void rootApiMessage() {
		try {
			this.mvc.perform(get("/").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk())
					.andExpect(content().string("Url to use: /hello/contacts?nameFilter=RegExp"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
