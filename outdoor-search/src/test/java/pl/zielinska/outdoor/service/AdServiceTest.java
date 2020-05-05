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
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.User;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.dto.ConverterDto;

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

    @MockBean
    private ConverterDto<Ad, AdDto> adConverter;

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
        List<AdDto> listDto = new ArrayList<>();
        listDto.add(Mockito.mock(AdDto.class));
        Mockito.when(adConverter.createFromEntities(any()))
                .thenReturn(listDto);

        List<AdDto> testDto = adService.findAllDto();
        assertEquals(listDto, testDto);
        Mockito.verify(adConverter, times(1)).createFromEntities(any());
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
        AdDto adDto = spy(new AdDto());
        Ad ad = Mockito.mock(Ad.class);
        Mockito.when(adConverter.createFrom(adDto))
                .thenReturn(ad);
        adService.publishNewAd(adDto, testUser);
        Mockito.verify(adRepository, times(1)).save(any());
        Mockito.verify(adConverter, times(1)).createFrom(adDto);
    }
}
