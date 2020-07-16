package com.yingchun.singlestone.assignment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yingchun.singlestone.assignment.model.Contact;
import com.yingchun.singlestone.assignment.repo.ContactRepository;
import com.yingchun.singlestone.assignment.service.ContactService;
import org.apache.logging.log4j.core.util.Assert;
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
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SingleStoneApplicationTests {

    private String contactJson = "{" +
            "\"phone\": [" +
            "{" +
            "\"number\": \"302-611-9148\"," +
            "\"type\": \"home\"" +
            "}," +
            "{" +
            "\"number\": \"302-532-9427\"," +
            "\"type\": \"mobile\"" +
            "}" +
            "]," +
            "\"name\": {" +
            "\"first\": \"Yingchun\"," +
            "\"middle\": \"Francis\"," +
            "\"last\": \"Gilkey\"" +
            "}," +
            "\"address\": {" +
            "\"street\": \"8360 High Autumn Row\"," +
            "\"city\": \"Cannon\"," +
            "\"state\": \"Delaware\"," +
            "\"zip\": \"19797\"" +
            "}," +
            "\"email\": \"harold.gilkey@yahoo.com\"" +
            "}";

    private String newContactJson = "{" +
            "\"phone\": [" +
            "{" +
            "\"number\": \"302-611-9148\"," +
            "\"type\": \"home\"" +
            "}," +
            "{" +
            "\"number\": \"302-532-9427\"," +
            "\"type\": \"mobile\"" +
            "}" +
            "]," +
            "\"name\": {" +
            "\"first\": \"Harold\"," +
            "\"middle\": \"Francis\"," +
            "\"last\": \"Gilkey\"" +
            "}," +
            "\"address\": {" +
            "\"street\": \"8360 High Autumn Row\"," +
            "\"city\": \"Cannon\"," +
            "\"state\": \"Delaware\"," +
            "\"zip\": \"19797\"" +
            "}," +
            "\"email\": \"harold.gilkey@yahoo.com\"" +
            "}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
//    @MockBean
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;
    
    public SingleStoneApplicationTests() {};

    @Before(value = "")
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        for(int i=0;i<5; i++) {
	        Contact contact = createTestContacts(contactJson);        
	        contactService.addContact(contact);
    	}
    }

    private Contact createTestContacts(String jsonString) {
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Contact contact = null;

        try {
            contact = new ObjectMapper().readValue(jsonString, Contact.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return contact;
//		return gson.fromJson(contactJson, Contact.class);
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

    @Test
    public void generalApiTest()
      throws Exception {
    	HttpHeaders headers = this.restTemplate.getForEntity("/contacts", String.class).getHeaders();

        mockMvc.perform(get("/contacts")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
    }

	@Test //post, save new contact
	public void saveContactsTest() throws Exception {
//		Contact newContact = createTestContacts(contactJson);

        /*ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(anObject );*/

         mockMvc.perform(
                post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newContactJson)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name.first").value("Harold"))
                .andExpect(jsonPath("$.name.last").value("Gilkey"));
    }

	@Test //put update existing contact
	public void updateContactByIdTest() throws Exception {
        Contact newContact = createTestContacts(contactJson);
        newContact = contactService.addContact(newContact);

        mockMvc.perform(
                put("/contacts/"+newContact.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newContactJson)
                        .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name.first").value("Harold"))
        .andExpect(jsonPath("$.name.last").value("Gilkey"));
    }

	@Test //delete delete contacts
	public void deleteContactByIdTest() throws Exception {
        Contact newContact = createTestContacts(contactJson);
        newContact = contactService.addContact(newContact);

        mockMvc.perform(
            delete("/contacts/"+newContact.getId())
        )
        .andExpect(status().isOk());

        Assert.isEmpty(contactService.getContactById(newContact.getId()));
    }

}
