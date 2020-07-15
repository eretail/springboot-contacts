package com.yingchun.singlestone.assignment;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SrpingRunner.class)
@SpringBootTest
class SingleStoneApplicationTests {
/*
	@Autowired
	private UserContactService userContactService;
	
	@MockBean
	private UserContactRepository userContactRepository;
	
	@Test
	public void getUsersTest() {
		when(userContactRepository.findAll()).thenReturn(Stream
				.of(new Contact(376, "Danile", 31, "USA"), new User(958, "Huy", 35, "UK")).collect(Collectors.toList()));
		assertEquals(2, service.getUsers().size());
	}

	@Test
	public void getUserbyAddressTest() {
		String address = "Bangalore";
		when(userContactRepository.findByAddress(address))
				.thenReturn(Stream.of(new User(376, "Danile", 31, "USA")).collect(Collectors.toList()));
		assertEquals(1, service.getUserbyAddress(address).size());
	}

	@Test
	public void saveUserTest() {
		User user = new User(999, "Pranya", 33, "Pune");
		when(userContactRepository.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}

	@Test
	public void deleteUserTest() {
		User user = new User(999, "Pranya", 33, "Pune");
		service.deleteUser(user);
		verify(userContactRepository, times(1)).delete(user);
	}
	
	@Test
	void contextLoads() {
	
	}*/

	
}
