package pl.zielinska.outdoor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.zielinska.model.repository.AdRepository;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.Coordinates;
import pl.zielinska.model.domain.User;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.dto.ConverterDto;
import pl.zielinska.outdoor.exception.NotFoundException;
import pl.zielinska.model.util.CoordinatesUtil;

import java.util.List;

@Service
@AllArgsConstructor @NoArgsConstructor
public class AdServiceImpl implements AdService{

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private ConverterDto<Ad, AdDto> adConverter;

    @Override
    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    @Override
    public List<AdDto> findAllDto() {
        return adConverter.createFromEntities(adRepository.findAll());
    }

    @Override
    public List<AdDto> findAllDto(Sort sort) {
        return adConverter.createFromEntities(adRepository.findAll(sort));
    }

    @Override
    public List<AdDto> findByTagsName(String name) {
        return adConverter.createFromEntities(adRepository.findByTagsName(name));
    }

    @Override
    public Ad findById(int id) {
        return adRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Ad not found."));
    }

    @Override
    public AdDto findByIdDto(int id) {
        return adConverter.createFrom(findById(id));
    }

    @Override
    public void save(Ad theAd) {
        adRepository.save(theAd);
    }

    @Override
    public Ad publishNewAd(AdDto adDto, User user) throws JsonProcessingException {
        final Coordinates xy = CoordinatesUtil.translateAdressToCoordinates(adDto.getCity(), adDto.getStreet());

        Ad ad = adConverter.createFrom(adDto);
        ad.setAdAuthor(user);
        ad.setCoordinates(xy);
        return adRepository.save(ad);
    }

}
