package com.yingchun.singlestone.assignment.controller;

import org.springframework.web.bind.annotation.RestController;

import com.yingchun.singlestone.assignment.model.Contact;
import com.yingchun.singlestone.assignment.service.ContactService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 1.	Create a new REST API using Java/JVM technologies with the following endpoints:
 * HTTP Method	Route	Description
 * GET	/contacts	List all contacts
 * POST	/contacts	Create a new contact
 * PUT	/contacts/{id}	Update a contact
 * GET	/contacts/{id}	Get a specific contact
 * DELETE	/contacts/{id}	Delete a contact
**/

@RestController
public class ContactController {

	@Autowired 
	private ContactService contactService;
	
	@GetMapping("/contacts")
	public List<Contact> findAllContacts() {
		return contactService.getAllContacts();
	}
	
	@PostMapping(path = "/contacts", consumes = "application/json", produces = "application/json")
	public Contact saveContact(@RequestBody Contact contact) {
		return	contactService.addContact(contact);
	}

	@PutMapping(path ="/contacts/{id}", consumes = "application/json", produces = "application/json")
	public Contact updateContact(@RequestBody Contact contact, @PathVariable Integer id) {

		return contactService.updateContact(contact, id);
	}

	@GetMapping(path = "/contacts/{id}", produces = "application/json")
	public Optional<Contact> findContactById(@PathVariable Integer id) {
		return contactService.getContactById(id);
	}

	@DeleteMapping(value="/contacts/{id}" )
	public void deleteContactById(@PathVariable Integer id) {
		 contactService.deleteContactById(id);
	}
}