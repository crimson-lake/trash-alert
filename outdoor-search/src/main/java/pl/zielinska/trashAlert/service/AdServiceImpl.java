package pl.zielinska.trashAlert.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zielinska.trashAlert.dao.AdRepository;
import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.domain.User;
import pl.zielinska.trashAlert.dto.AdDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor @NoArgsConstructor
public class AdServiceImpl implements AdService{

    @Autowired
    private AdRepository adRepository;

    @Override
    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    @Override
    public List<AdDto> findAllDto() {
        return adRepository
                .findAll()
                .stream()
                .map(Ad::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Ad findById(int id) { //TODO Optional
        return adRepository.findById(id).get();
    }

    @Override
    public void save(Ad theAd) {
        adRepository.save(theAd);
    }

    @Override
    public Ad publishNewAd(AdDto adDto, User user) {
        Ad newAd = Ad.builder()
                        .title(adDto.getTitle())
                        .details(adDto.getDetails())
                        .city(adDto.getCity())
                        .street(adDto.getStreet())
                        .created(LocalDateTime.now())
                        .adAuthor(user)
                        .build();
        return adRepository.save(newAd);
    }

}
