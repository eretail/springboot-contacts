package com.yingchun.singlestone.assignment;

import java.util.stream.Collectors;

import com.yingchun.singlestone.assignment.repo.ContactRepository;
import com.yingchun.singlestone.assignment.service.ContactService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SrpingRunner.class)
@WebMvcTest
@SpringBootTest
class SingleStoneApplicationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContactService contactService;

    @MockBean
    private ContactRepository userContactRepository;

    @Before
    public void setUp() {
        Employee alex = new Employee("alex");

        Mockito.when(employeeRepository.findByName(alex.getName()))
                .thenReturn(alex);
    }

    @Test
    public void getContactShouldReturnDetails() {
        given(this.contactService.schedulePickup(new Date(), new Route());)
            .willReturn(new Date());

        this.mvc.perform(get("/schedulePickup")
                .accept(MediaType.JSON)
                .andExpect(status().isOk());
    }

	@Test
	public void getContactsTest() {
		when(userContactRepository.findAll()).thenReturn(Stream
				.of(new Contact(376, "Danile", 31, "USA"), new User(958, "Huy", 35, "UK")).collect(Collectors.toList()));
		assertEquals(2, service.getUsers().size());
	}

	@Test
	public void getContactByIdTest() {
		String address = "Bangalore";
		when(userContactRepository.findByAddress(address))
				.thenReturn(Stream.of(new User(376, "Danile", 31, "USA")).collect(Collectors.toList()));
		assertEquals(1, service.getUserbyAddress(address).size());
	}

	@Test
	public void saveContactTest() {
		User user = new User(999, "Pranya", 33, "Pune");
		when(userContactRepository.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}

	@Test
	public void deleteContactByIdTest() {
		User user = new User(999, "Pranya", 33, "Pune");
		service.deleteUser(user);
		verify(userContactRepository, times(1)).delete(user);
	}
	
	@Test
	void contextLoads() {
	
	}

	
}
