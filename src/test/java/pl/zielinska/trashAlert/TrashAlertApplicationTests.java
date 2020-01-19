package pl.zielinska.trashAlert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zielinska.trashAlert.dao.UserRepository;
import pl.zielinska.trashAlert.entity.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrashAlertApplicationTests {

	@Autowired
	private UserRepository userRepository;

	private final String testUsername = "patick";
	private final String testFirstName = "Patryk";
	private final String testLastName = "Trynk";
	private final String testEmail = "pt@outlook.com";

	@Test
	void contextLoads() {
	}

	@Test
	void testCreateUser() {
		User user = User.builder()
						.username(testUsername)
						.firstName(testFirstName)
						.lastName(testLastName)
						.email(testEmail)
						.build();

		userRepository.save(user);
	}

	@Test
	void testReadUser() {
		User user = userRepository.findByUsername(testUsername);
		assertNotNull(user);
		assertEquals(testUsername, user.getUsername());
		assertEquals(testFirstName, user.getFirstName());
		assertEquals(testLastName, user.getLastName());
		assertEquals(testEmail, user.getEmail());
	}

	@Test
	void testUpdateUser() {
		User user = userRepository.findByUsername(testUsername);
		String updatedEmail = "newTestEmail@protonmail.com";
		user.setEmail(updatedEmail);
		userRepository.save(user);

		user = userRepository.findByUsername(testUsername);
		assertEquals(updatedEmail, user.getEmail());
	}

	@Test
	void testDeleteUser() {
		User user = userRepository.findByUsername(testUsername);
		userRepository.delete(user);
		assertNull(userRepository.findByUsername(testUsername));
	}

}
