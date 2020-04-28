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
import pl.zielinska.outdoor.dto.AdDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class AdServiceTest {

    @TestConfiguration
    static class AdServiceImplTestContextConfiguration {

        @Bean
        public AdService adService() {
            return new AdServiceImpl();
        }
    }

    @Autowired
    private AdService adService;

    @MockBean
    private AdRepository adRepository;

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

    private Ad testAd = Ad.builder()
            .id(TestVal.TEST_ID)
            .title(TestVal.TEST_TITLE)
            .city(TestVal.TEST_CITY)
            .street(TestVal.TEST_STREET)
            .created(TestVal.TEST_TIME)
            .adAuthor(testUser)
            .build();

    private List<Ad> testAdsList = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < TEST_SIZE; i++) {
            testAdsList.add(Ad.builder()
                    .id(TestVal.TEST_ID + i)
                    .title(TestVal.TEST_TITLE)
                    .city(TestVal.TEST_CITY)
                    .street(TestVal.TEST_STREET)
                    .created(TestVal.TEST_TIME)
                    .adAuthor(testUser)
                    .build());
        }
        assertEquals(TEST_SIZE, testAdsList.size());
    }

    @Test
    public void findAllTest() {
        Mockito.when(adRepository.findAll())
                .thenReturn(testAdsList);

        assertEquals(testAdsList, adService.findAll());
    }

    @Test
    public void findAllTestDto() {
        Mockito.when(adRepository.findAll())
                .thenReturn(testAdsList);

        List<AdDto> testDto = adService.findAllDto();
        assertEquals(TEST_SIZE, testDto.size());
        Mockito.verify(adRepository, times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        Mockito.when(adRepository.findById(anyInt()))
                .thenReturn(Optional.of(testAd));

        assertEquals(testAd, adService.findById(TestVal.TEST_ID));
        Mockito.verify(adRepository, times(1)).findById(TestVal.TEST_ID);
    }

    @Test
    public void saveTest() {
        adService.save(testAd);
        Mockito.verify(adRepository, times(1)).save(testAd);
    }

    @Test
    public void publishNewAd() throws JsonProcessingException {
        AdDto adDto = spy(testAd.toDto());
        adService.publishNewAd(adDto, testUser);
        Mockito.verify(adDto, times(1)).getTitle();
        Mockito.verify(adDto, times(2)).getCity();
        Mockito.verify(adDto, times(1)).getDetails();
        Mockito.verify(adDto, times(2)).getStreet();
        Mockito.verify(adRepository, times(1)).save(any());
    }
}
