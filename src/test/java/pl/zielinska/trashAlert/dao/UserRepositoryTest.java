package pl.zielinska.trashAlert.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.zielinska.trashAlert.TestVal;
import pl.zielinska.trashAlert.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testCreateUser() {
		User user = User.builder()
						.username(TestVal.TEST_USERNAME)
						.firstName(TestVal.TEST_FIRST_NAME)
						.lastName(TestVal.TEST_LAST_NAME)
						.email(TestVal.TEST_EMAIL)
						.password(TestVal.TEST_PASSWORD)
						.authority("USER")
						.enabled(true)
						.build();

		userRepository.save(user);
	}

	@Test
	void testReadUser() {
		User user = userRepository.findByUsername(TestVal.TEST_USERNAME);
		user.getAds();
		user.getComments();
		assertNotNull(user);
		assertEquals(TestVal.TEST_USERNAME, user.getUsername());
		assertEquals(TestVal.TEST_FIRST_NAME, user.getFirstName());
		assertEquals(TestVal.TEST_LAST_NAME, user.getLastName());
		assertEquals(TestVal.TEST_EMAIL, user.getEmail());
	}

	@Test
	void testUpdateUser() {
		User user = userRepository.findByUsername(TestVal.TEST_USERNAME);
		String updatedEmail = "newTEST_EMAIL@protonmail.com";
		user.setEmail(updatedEmail);
		userRepository.save(user);

		user = userRepository.findByUsername(TestVal.TEST_USERNAME);
		assertEquals(updatedEmail, user.getEmail());
	}

	@Test
	void testDeleteUser() {
		User user = userRepository.findByUsername(TestVal.TEST_USERNAME);
		userRepository.delete(user);
		assertNull(userRepository.findByUsername(TestVal.TEST_USERNAME));
	}

	@Test
	void generatePassword() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		log.info(encoder.encode("123456"));
	}

}
