package com.yingchun.singlestone.assignment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yingchun.singlestone.assignment.model.Contact;
import com.yingchun.singlestone.assignment.repo.ContactRepository;
import com.yingchun.singlestone.assignment.service.ContactService;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SingleStoneApplicationTests {

	private final String contactJson = "	{\r\n" + 
			"	   \"phone\": [\r\n" + 
			"	    {\r\n" + 
			"	      \"number\": \"302-611-9148\",\r\n" + 
			"	      \"type\": \"home\"\r\n" + 
			"	    },\r\n" + 
			"	    {\r\n" + 
			"	      \"number\": \"302-532-9427\",\r\n" + 
			"	      \"type\": \"mobile\"\r\n" + 
			"	    }\r\n" + 
			"	  ],\r\n" + 
			"      \"name\": {\r\n" + 
			"	    \"first\": \"Yingchun\",\r\n" + 
			"	    \"middle\": \"Francis\",\r\n" + 
			"	    \"last\": \"Gilkey\"\r\n" + 
			"	  },\r\n" + 
			"	  \"address\": {\r\n" + 
			"	    \"street\": \"8360 High Autumn Row\",\r\n" + 
			"	    \"city\": \"Cannon\",\r\n" + 
			"	    \"state\": \"Delaware\",\r\n" + 
			"	    \"zip\": \"19797\"\r\n" + 
			"	  },	 \r\n" + 
			"	  \"email\": \"harold.gilkey@yahoo.com\"\r\n" + 
			"	}";
	
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;
    
    public SingleStoneApplicationTests() {};
    @Before(value = "")
    public void setUp() {
    	for(int i=0;i<5; i++) {
	        Contact contact = createTestContacts(contactJson);        
	        contactService.addContact(contact);
    	}
    }
    
	private Contact createTestContacts(String contactJson) {
		Gson gson = new Gson();
		
		return gson.fromJson(contactJson, Contact.class); 
	}
	    
    @Test //getContactById
    public void testGetContacts()
      throws Exception {
     
        Contact contact = createTestContacts(contactJson);
     
        contactService.addContact(contact);

        assertThat(((Contact)contactService.findById(1).get()).getContactName().getFirstName().equals("Yingchun"));
        ResponseEntity<Contact> responseEntity = restTemplate.getForEntity("/contacts/{id}",Contact.class,"1");
        assertThat(responseEntity.getBody().getContactName().getFirstName().equals("Yingchun"));
    }

    @Test //getContacts
    public void givenContact_whenGetContacts_thenStatus200()
      throws Exception {
    
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();

        mvc.perform(get("/contacts")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect((ResultMatcher) jsonPath("$[0].first", is("Yingchun")));
    }

	@Test //post, save new contact
	public void saveContactsTest() {
		Contact newContact = createTestContacts(contactJson);
	}

	@Test
	public void updateContactByIdTest() {
	}


	@Test
	public void deleteContactByIdTest() {
	}

	
	@Test
	public void testRequest() {
		HttpHeaders headers = this.restTemplate.getForEntity("/contacts", String.class).getHeaders();
		assertThat(headers.getLocation()).hasHost("127.0.0.1");
	}

}
