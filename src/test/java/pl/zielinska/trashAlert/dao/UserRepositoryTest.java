package pl.zielinska.trashAlert.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.zielinska.trashAlert.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private final String testUsername = "patick";
	private final String testFirstName = "Patryk";
	private final String testLastName = "Trynk";
	private final String testEmail = "pt@outlook.com";
	private final String testPassword = "123456";

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
						.password(testPassword)
						.authority("USER")
						.enabled(true)
						.build();

		userRepository.save(user);
	}

	@Test
	void testReadUser() {
		User user = userRepository.findByUsername(testUsername);
		user.getAds();
		user.getComments();
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

	@Test
	void generatePassword() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		log.info(encoder.encode("123456"));
	}

}
