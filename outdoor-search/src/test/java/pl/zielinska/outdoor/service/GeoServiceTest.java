package pl.zielinska.outdoor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.zielinska.outdoor.TestVal;
import pl.zielinska.outdoor.dao.AdRepository;
import pl.zielinska.outdoor.domain.Ad;
import pl.zielinska.outdoor.domain.User;
import pl.zielinska.outdoor.geoJSON.GeoJSONCollection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class GeoServiceTest {

    @TestConfiguration
    static class GeoServiceImplTestContextConfiguration {

        @Bean
        public GeoService geoService() {
            return new GeoServiceImpl();
        }
    }

    @Autowired
    private GeoService geoService;

    @MockBean
    private AdRepository adRepository;

    private User testUser = User.builder()
            .username(TestVal.TEST_USERNAME)
            .firstName(TestVal.TEST_FIRST_NAME)
            .lastName(TestVal.TEST_LAST_NAME)
            .email(TestVal.TEST_EMAIL)
            .password(TestVal.TEST_PASSWORD)
            .authority("USER")
            .enabled(true)
            .build();

    private Ad testAd = Ad.builder()
            .id(TestVal.TEST_ID)
            .title(TestVal.TEST_TITLE)
            .city(TestVal.TEST_CITY)
            .street(TestVal.TEST_STREET)
            .created(TestVal.TEST_TIME)
            .adAuthor(testUser)
            .build();

    @Before
    public void setUp() {
        Mockito.when(adRepository.findById(TestVal.TEST_ID))
                .thenReturn(java.util.Optional.ofNullable(testAd));
    }

    @Test
    public void getCoordinatesTest() throws JsonProcessingException {
        GeoJSONCollection allCoordinates = geoService.getCoordinates();
        assertNotNull(allCoordinates);
    }
}
