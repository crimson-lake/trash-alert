package pl.zielinska.outdoor.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import pl.zielinska.outdoor.TestVal;
import pl.zielinska.outdoor.dao.UserRepository;
import pl.zielinska.outdoor.domain.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
public class UserDetailsServiceTest {

    @TestConfiguration
    static class UserDetailsServiceTestContextConfiguration {

        @Bean
        public UserDetailsService userDetailsService() {
            return new UserDetailsServiceImpl();
        }
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @MockBean
    private UserService userService;

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
    public void loadUserByUsernameTest() {
        Mockito.when(userService.findByUsername(TestVal.TEST_USERNAME))
                .thenReturn(testUser);
        assertEquals(testUser, userDetailsService.loadUserByUsername(TestVal.TEST_USERNAME));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameExceptionTest() {
        Mockito.when(userService.findByUsername(TestVal.TEST_USERNAME))
                .thenReturn(null);
        assertEquals(testUser, userDetailsService.loadUserByUsername(TestVal.TEST_USERNAME));
    }
}
