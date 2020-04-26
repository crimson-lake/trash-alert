package pl.zielinska.outdoor.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.zielinska.outdoor.TestVal;
import pl.zielinska.outdoor.domain.Ad;
import pl.zielinska.outdoor.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class AdRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

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
    public void createAdTest() {
        entityManager.persist(testUser);
        entityManager.flush();

        Ad ad = Ad.builder()
                .title(TestVal.TEST_TITLE)
                .city(TestVal.TEST_CITY)
                .street(TestVal.TEST_STREET)
                .created(TestVal.TEST_TIME)
                .adAuthor(testUser)
                .build();

        assertNotNull(ad.getAdAuthor());
        assertEquals(testUser, ad.getAdAuthor());

        entityManager.persist(ad);
        entityManager.flush();
    }

    @Test
    public void findByAdAuthorTest() {
        User user = userRepository.findByUsername(TestVal.TEST_USERNAME);
        assertNotNull(adRepository.findByAdAuthor(user));
    }

    @Test
    public void deleteUserWithAdTest() {
        entityManager.persistAndFlush(testUser);
        User user = userRepository.findByUsername(TestVal.TEST_USERNAME);
        assertNotNull(user);

        entityManager.remove(user);
        assertNull(userRepository.findByUsername(TestVal.TEST_USERNAME));
        assertEquals(0, adRepository.findByAdAuthor(user).size());
    }
}
