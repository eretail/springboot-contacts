package com.yingchun.singlestone.assignment.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yingchun.singlestone.assignment.exception.ResourceNotFoundException;
import com.yingchun.singlestone.assignment.model.Contact;
import com.yingchun.singlestone.assignment.service.ContactService;
import com.yingchun.singlestone.assignment.util.ResponseUtil;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

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
	
	@GetMapping(path="/contacts", produces = "application/json")
	public List<Contact> findAllContacts() {
		return contactService.getAllContacts();
	}
	
	@PostMapping(path = "/contacts", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Contact> saveContact(@RequestBody Contact contact) throws Throwable {
		
		Optional<Contact> contactOptional = Optional.of(contactService.addContact(contact));
		
		return (ResponseEntity<Contact>) contactOptional.map( c -> 
				ResponseEntity.created(ResponseUtil.resourceUri(contact.getId()))
				.body(c)
		).orElseThrow(()-> new ResourceNotFoundException(
            "Contact: " + contact.getContactName().getFirstName() + " not created.")
			);
	}

	@PutMapping(path ="/contacts/{contactId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Contact> updateContact(@RequestBody Contact contact, @PathVariable Integer contactId) throws Throwable {
		Optional<Contact> contactOptional = contactService.updateContact(contact, contactId);
 		return (ResponseEntity<Contact>) 
 				contactOptional.map(c ->
					ResponseEntity
					.ok()
					.location(ResponseUtil.resourceUri(contactId))
 					.body(c)
            )
            .orElseThrow(() -> new ResourceNotFoundException(
                            "Contact: " + contactId + " not found"
                    )
            );
	}

	@GetMapping(path = "/contacts/{id}", produces = "application/json")
	public ResponseEntity<Contact>  findContactById(@PathVariable Integer id) throws Throwable {
		Optional<Contact> contactOptional = contactService.getContactById(id);
		return  (ResponseEntity<Contact>) contactOptional.map(c ->
					ResponseEntity
							.ok()
							.location(ResponseUtil.resourceUri(id))
							.body(c)
				)
			.orElseThrow(() -> new ResourceNotFoundException(
			                "ContactId: " + id + " not found"
			        )
			);
	}

	@SuppressWarnings("unchecked")
	@DeleteMapping(value="/contacts/{contactId}", produces = "application/text")
	public ResponseEntity<String> deleteContactById(@PathVariable Integer contactId) throws Throwable {
		 
		 return (ResponseEntity<String>) contactService.findById(contactId)
	                .map(contact -> {
	                	contactService.deleteContactById(Integer.valueOf(contactId));
	                    return  ResponseEntity
	                            .ok()
	                            .body("ContactID " + contactId + " is deleted");
	                })
	                .orElseThrow(() -> new ResourceNotFoundException(
	                                "ContactID " + contactId + " not found"
	                        )
	                );
	}
}