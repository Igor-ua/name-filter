package org.task.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.task.core.Contact;
import org.task.core.ContactsService;
import org.task.core.Page;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration
@DataJpaTest(showSql = false)
@ComponentScan("org.task")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ContactsServiceTest {

	@Autowired
	private ContactsService cs;

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
	public void findPage() {
		List<Contact> result = new ArrayList<>();
		Contact c1 = new Contact(9L);
		c1.setName("Mike_9");
		result.add(c1);
		Page p1 = cs.getContacts(DEFAULT_REGEXP, 1);
		Page p2 = new Page(result, 1, pageSize, false);
		assertThat(p1).isEqualTo(defaultPage);
	}
}
