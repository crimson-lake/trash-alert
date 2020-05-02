package pl.zielinska.outdoor.service;

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
import pl.zielinska.outdoor.TestVal;
import pl.zielinska.outdoor.dao.UserRepository;
import pl.zielinska.outdoor.domain.Ad;
import pl.zielinska.outdoor.domain.User;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.dto.UserDto;
import pl.zielinska.outdoor.exception.NotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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

    @Test
    public void findByUsernameTest() {
        Mockito.when(userRepository.findByUsername(TestVal.TEST_USERNAME))
                .thenReturn(Optional.of(testUser));
        assertEquals(testUser, userService.findByUsername(TestVal.TEST_USERNAME));
        Mockito.verify(userRepository, times(1)).findByUsername(TestVal.TEST_USERNAME);
    }

    @Test(expected = NotFoundException.class)
    public void findByUsernameInvalidUsernameTest() {
        final String INVALID_USERNAME = "invalid_username";
        userService.findByUsername(INVALID_USERNAME);
    }

    @Test
    public void findByEmailTest() {
        Mockito.when(userRepository.findByEmail(TestVal.TEST_EMAIL))
                .thenReturn(Optional.of(testUser));
        assertEquals(testUser, userService.findByEmail(TestVal.TEST_EMAIL));
        Mockito.verify(userRepository, times(1)).findByEmail(TestVal.TEST_EMAIL);
    }

    @Test
    public void saveTest() {
        userService.save(testUser);
        Mockito.verify(userRepository, times(1)).save(testUser);
    }

    @Test
    public void bindAdWithUserTest() {
        User testUser = mock(User.class);
        Ad testAd = mock(Ad.class);
        Mockito.doNothing().when(testUser).addNewAd(any());
        userService.bindAdWithUser(testUser, testAd);
        Mockito.verify(testUser, times(1)).addNewAd(testAd);
        Mockito.verify(userRepository, times(1)).save(testUser);
    }

    @Test
    public void getUsersAdsDtoTest() {
        User testUser = mock(User.class);
        Ad testAd = mock(Ad.class);
        Set<Ad> adSet = new HashSet<>();
        adSet.add(testAd);

        Mockito.when(userRepository.findByUsername(TestVal.TEST_USERNAME))
                .thenReturn(Optional.of(testUser));
        Mockito.when(testUser.getAds())
                .thenReturn(adSet);
        Mockito.when(testAd.toDto())
                .thenReturn(new AdDto());

        userService.usersAdsDto(TestVal.TEST_USERNAME);

        Mockito.verify(userRepository, times(1)).findByUsername(TestVal.TEST_USERNAME);
        Mockito.verify(testUser, times(1)).getAds();
        Mockito.verify(testAd, times(1)).toDto();
    }

    @Test
    public void registerNewUserTest() {
        UserDto testUserDto = mock(UserDto.class);
        userService.registerNewUserAccount(testUserDto);
        Mockito.verify(testUserDto, times(1)).getUsername();
        Mockito.verify(testUserDto, times(1)).getFirstName();
        Mockito.verify(testUserDto, times(1)).getLastName();
        Mockito.verify(testUserDto, times(1)).getEmail();
        Mockito.verify(testUserDto, times(1)).getDefaultCity();
        Mockito.verify(testUserDto, times(1)).getPassword();
        Mockito.verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void usernameAvailableTest() {
        Mockito.when(userRepository.findByUsername(TestVal.TEST_USERNAME))
                .thenReturn(Optional.of(testUser));
        assertEquals(false, userService.usernameAvailable(TestVal.TEST_USERNAME));
    }

    @Test
    public void usernameUnavailableTest() {
        Mockito.when(userRepository.findByUsername(TestVal.TEST_USERNAME))
                .thenReturn(Optional.ofNullable(null));
        assertEquals(true, userService.usernameAvailable(TestVal.TEST_USERNAME));
    }

    @Test
    public void emailAvailableTest() {
        Mockito.when(userRepository.findByEmail(TestVal.TEST_EMAIL))
                .thenReturn(Optional.of(testUser));
        assertEquals(false, userService.emailAvailable(TestVal.TEST_EMAIL));
    }

    @Test
    public void emailUnavailableTest() {
        Mockito.when(userRepository.findByEmail(TestVal.TEST_EMAIL))
                .thenReturn(Optional.ofNullable(null));
        assertEquals(true, userService.emailAvailable(TestVal.TEST_EMAIL));
    }
}
