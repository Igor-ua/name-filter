package org.task.core;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "contacts")
public class Contact {
	@Id
	@GeneratedValue
	@JsonView(View.Summary.class)
	@Column(name = "id")
	private Long id;

	@JsonView(View.Summary.class)
	@Column(name = "name")
	private String name;

	public Contact() {
	}

	public Contact(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Contact contact = (Contact) o;

		if (id != null ? !id.equals(contact.id) : contact.id != null) return false;
		return name != null ? name.equals(contact.name) : contact.name == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", name='" + name + "'}";
	}
}
