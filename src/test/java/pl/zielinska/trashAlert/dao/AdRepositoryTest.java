package pl.zielinska.trashAlert.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zielinska.trashAlert.TestVal;
import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class AdRepositoryTest {

    @Autowired
    private AdRepository adRepository;

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
    void createAdTest() {
        userRepository.save(testUser);
        Ad ad = Ad.builder()
                .title(TestVal.TEST_TITLE)
                .city(TestVal.TEST_CITY)
                .street(TestVal.TEST_STREET)
                .created(TestVal.TEST_TIME)
                .adAuthor(testUser)
                .build();

        assertNotNull(ad.getAdAuthor());
        assertEquals(testUser, ad.getAdAuthor());
        adRepository.save(ad);
    }

    @Test
    void findByAdAuthorTest() {
        User user = userRepository.findByUsername(TestVal.TEST_USERNAME);
        assertNotNull(adRepository.findByAdAuthor(user));
    }

    @Test
    void deleteUserWithAdTest() {
        User user = userRepository.findByUsername(TestVal.TEST_USERNAME);
        userRepository.delete(user);
        assertNull(userRepository.findByUsername(TestVal.TEST_USERNAME));
        assertEquals(0, adRepository.findByAdAuthor(user).size());
    }
}
