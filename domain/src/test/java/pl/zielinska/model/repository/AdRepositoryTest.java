package pl.zielinska.model.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.zielinska.model.TestVal;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.Coordinates;
import pl.zielinska.model.domain.User;

import java.util.Optional;

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
        entityManager.persistAndFlush(testUser);

        Ad ad = Ad.builder()
                .title(TestVal.TEST_TITLE)
                .city(TestVal.TEST_CITY)
                .street(TestVal.TEST_STREET)
                .created(TestVal.TEST_TIME)
                .adAuthor(testUser)
                .coordinates(TestVal.TEST_COORDINATES)
                .build();

        assertNotNull(ad.getAdAuthor());
        assertEquals(testUser, ad.getAdAuthor());

        entityManager.persistAndFlush(ad);
    }

    @Test
    public void findByAdAuthorTest() {
        entityManager.persistAndFlush(testUser);
        User user = userRepository.findByUsername(TestVal.TEST_USERNAME).get();
        assertNotNull(adRepository.findByAdAuthor(user));
    }

    @Test
    public void deleteUserWithAdTest() {
        entityManager.persistAndFlush(testUser);
        Optional<User> user = userRepository.findByUsername(TestVal.TEST_USERNAME);
        assertNotNull(user);

        entityManager.remove(user.get());
        assertFalse(userRepository.findByUsername(TestVal.TEST_USERNAME).isPresent());
        assertEquals(0, adRepository.findByAdAuthor(user.get()).size());
    }

    @Test
    public void coordinatesPersistingTest() {
        entityManager.persistAndFlush(testUser);
        Coordinates xy = new Coordinates(0, TestVal.TEST_COORDINATES_X, TestVal.TEST_COORDINATES_Y);
        Ad ad = Ad.builder()
                .title(TestVal.TEST_TITLE)
                .city(TestVal.TEST_CITY)
                .street(TestVal.TEST_STREET)
                .created(TestVal.TEST_TIME)
                .adAuthor(testUser)
                .coordinates(xy)
                .build();
        assertNotNull(ad.getCoordinates());
        entityManager.persistAndFlush(ad);

        Ad persistedAd = adRepository.findAll().get(0);
        assertNotNull(persistedAd);
        assertNotNull(persistedAd.getCoordinates());
    }
}
