package pl.zielinska.trashAlert.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import pl.zielinska.trashAlert.TestVal;
import pl.zielinska.trashAlert.dao.UserRepository;
import pl.zielinska.trashAlert.domain.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }

    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder encoder;

    private final int TEST_SIZE = 10;

    private User testUser = User.builder()
            .username(TestVal.TEST_USERNAME)
            .firstName(TestVal.TEST_FIRST_NAME)
            .lastName(TestVal.TEST_LAST_NAME)
            .email(TestVal.TEST_EMAIL)
            .password(TestVal.TEST_PASSWORD)
            .authority("USER")
            .enabled(true)
            .build();

    private List<User> testUsersList = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < TEST_SIZE; i++) {
            testUsersList.add(User.builder()
                    .id(TestVal.TEST_ID + i)
                    .username(TestVal.TEST_USERNAME + i)
                    .firstName(TestVal.TEST_FIRST_NAME)
                    .lastName(TestVal.TEST_LAST_NAME)
                    .email(TestVal.TEST_EMAIL + i)
                    .password(TestVal.TEST_PASSWORD)
                    .authority("USER")
                    .enabled(true)
                    .build());
        }
        assertEquals(TEST_SIZE, testUsersList.size());
    }

    @Test
    public void findAllTest() {
        Mockito.when(userRepository.findAll())
                .thenReturn(testUsersList);
        assertEquals(testUsersList, userService.findAll());
        Mockito.verify(userRepository, times(1)).findAll();
    }
}
