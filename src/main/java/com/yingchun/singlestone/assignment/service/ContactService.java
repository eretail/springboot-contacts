package com.yingchun.singlestone.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yingchun.singlestone.assignment.model.Contact;
import com.yingchun.singlestone.assignment.repo.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	public List<Contact> getAllContacts() {
		
		return (List<Contact>) contactRepository.findAll();
	}

	public Optional<Contact> addContact(Contact contact) {
		Optional<Contact> cont= Optional.empty();
		try {
			 cont.of(contactRepository.save(contact));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cont;
	}

	public Contact updateContact(Contact contact) {
		return contactRepository.save(contact);
	}

	public Optional<Contact> getContactById(Integer id) {
		return contactRepository.findById(id);
	}

	public void deleteContactById(Integer id) {
		contactRepository.deleteById(id);
	}

}
