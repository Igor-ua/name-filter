package org.task.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


/**
 * Utility class that fills db with a content size of "ENTRY_QUANTITY"
 */
@Component
public class ContentManager {

	private int ENTRY_QUANTITY = 100;

	@Autowired
	private ContactRepository contactsRepo;

	@PostConstruct
	public void fillDb() {
		List<Contact> contacts = new ArrayList<>(ENTRY_QUANTITY);
		for (int i = 0; i < ENTRY_QUANTITY; i++) {
			Contact contact = new Contact();
			contact.setName("Mike");
			contacts.add(contact);
		}
		contactsRepo.save(contacts);
	}
}
