package org.task.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactsService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${page.size}")
	private Integer pageSize;

	public Page getContacts(String regexp, int pageNumber) {

		/**
		 * Condition: not to filter any data on the db level
		 */
		String sql = "SELECT * FROM CONTACTS";

		Page page = jdbcTemplate.query(sql, rs -> {
			List<Contact> result = new ArrayList<>();
			int foundValidNames = 0;
			int validNamesToSkip = (pageNumber - 1) * pageSize;
			boolean hasMore = false;

			while (rs.next()) {
				String name = rs.getString("NAME");
				boolean isNameValid = !name.matches(regexp);
				if (foundValidNames >= (pageSize + validNamesToSkip)) {
					// Looking for one more element to define is one more page available
					if (isNameValid) {
						hasMore = true;
						break;
					}
				} else {
					if (isNameValid) {
						foundValidNames++;
					}
					if (isNameValid && foundValidNames > validNamesToSkip) {
						Contact contact = new Contact(rs.getLong("ID"));
						contact.setName(name);
						result.add(contact);
					}
				}
			}
			return new Page(result, pageNumber, pageSize, hasMore);
		});

		return page;
	}
}
