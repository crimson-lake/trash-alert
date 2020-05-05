package pl.zielinska.outdoor.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.zielinska.outdoor.TestVal;
import pl.zielinska.model.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	private User testUser = User.builder()
			.username(TestVal.TEST_USERNAME)
			.firstName(TestVal.TEST_FIRST_NAME)
			.lastName(TestVal.TEST_LAST_NAME)
			.email(TestVal.TEST_EMAIL)
			.password(TestVal.TEST_PASSWORD)
			.authority("USER")
			.enabled(true)
			.build();

	@Test
	public void findByUsernameTest() {
		entityManager.persistAndFlush(testUser);
		User user = userRepository.findByUsername(TestVal.TEST_USERNAME).get();

		assertNotNull(user);
		assertEquals(TestVal.TEST_USERNAME, user.getUsername());
		assertEquals(TestVal.TEST_FIRST_NAME, user.getFirstName());
		assertEquals(TestVal.TEST_LAST_NAME, user.getLastName());
		assertEquals(TestVal.TEST_EMAIL, user.getEmail());
	}

}
