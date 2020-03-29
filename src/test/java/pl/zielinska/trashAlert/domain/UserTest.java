package pl.zielinska.trashAlert.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zielinska.trashAlert.TestVal;
import pl.zielinska.trashAlert.dao.UserRepository;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setup() {
        testUser = User.builder()
                .username(TestVal.TEST_USERNAME)
                .firstName(TestVal.TEST_FIRST_NAME)
                .lastName(TestVal.TEST_LAST_NAME)
                .email(TestVal.TEST_EMAIL)
                .password(TestVal.TEST_PASSWORD)
                .authority("USER")
                .enabled(true)
                .build();
    }

    @Test
    void invalidEmailTest() {
        testUser.setEmail(TestVal.INVALID_EMAIL);
        assertThrows(ConstraintViolationException.class, () ->
                userRepository.save(testUser));
    }

    @Test
    void invalidUsername() {
        testUser.setUsername(null);
        assertThrows(ConstraintViolationException.class, () ->
                userRepository.save(testUser));

        testUser.setUsername("");
        assertThrows(ConstraintViolationException.class, () ->
                userRepository.save(testUser));

        testUser.setUsername(" ");
        assertThrows(ConstraintViolationException.class, () ->
                userRepository.save(testUser));

        String invalidLength = "abc";
        testUser.setUsername(invalidLength);

        assertThrows(ConstraintViolationException.class, () ->
                userRepository.save(testUser));
    }

}
