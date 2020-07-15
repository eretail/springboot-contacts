package com.yingchun.singlestone.assignment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.yingchun.singlestone.assignment.model.ContactPhone;
import com.yingchun.singlestone.assignment.repo.ContactPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yingchun.singlestone.assignment.model.Contact;
import com.yingchun.singlestone.assignment.repo.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ContactPhoneRepository contactPhoneRepository;

	public List<Contact> getAllContacts() {
		
		return (List<Contact>) contactRepository.findAll();
	}

	public Contact addContact(Contact contact) {

		try {
			contact = contactRepository.save(contact);
			Integer contactId = contact.getId();
			contact.getContactPhones().stream()
					.forEach(c -> {
						c.setContactId(contactId);
						contactPhoneRepository.save(c);
					});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return contact;
	}

	public Contact updateContact(Contact contact, Integer id) {
		if(contactRepository.existsById(id))
			contact.setId(id);

		try {
			contact = contactRepository.save(contact);
			Integer contactId = contact.getId();
			contact.getContactPhones().stream()
					.forEach(c -> {
						c.setContactId(contactId);
						contactPhoneRepository.save(c);
					});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return contact;
	}

	public Optional<Contact> getContactById(Integer id) {
		return contactRepository.findById(id);
	}

	public void deleteContactById(Integer id) {
		contactRepository.deleteById(id);
	}

}
