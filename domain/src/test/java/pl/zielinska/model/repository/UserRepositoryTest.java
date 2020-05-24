package pl.zielinska.model.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.zielinska.model.TestVal;
import pl.zielinska.model.domain.User;

import static org.junit.Assert.assertNotNull;


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
		Assertions.assertEquals(TestVal.TEST_USERNAME, user.getUsername());
		Assertions.assertEquals(TestVal.TEST_FIRST_NAME, user.getFirstName());
		Assertions.assertEquals(TestVal.TEST_LAST_NAME, user.getLastName());
		Assertions.assertEquals(TestVal.TEST_EMAIL, user.getEmail());
	}

}
