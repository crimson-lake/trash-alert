package pl.zielinska.outdoor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.zielinska.model.domain.SortAndFilterArguments;
import pl.zielinska.model.repository.AdRepository;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.Coordinates;
import pl.zielinska.model.domain.User;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.dto.ConverterDto;
import pl.zielinska.outdoor.exception.NotFoundException;
import pl.zielinska.model.util.CoordinatesUtil;

import java.util.Map;

@Service
@AllArgsConstructor @NoArgsConstructor
public class AdServiceImpl implements AdService{

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private ConverterDto<Ad, AdDto> adConverter;

    @Override
    public Page<Ad> findAll(Pageable pageable) {
        return adRepository.findAll(pageable);
    }

    @Override
    public Page<AdDto> findAllDto(Pageable pageable) {
        return adRepository.findAll(pageable)
                           .map(adConverter::createFrom);
    }

    @Override
    public Page<AdDto> findAllDto(SortAndFilterArguments sortAndFilterArgs, Pageable pageable) {
        if (sortAndFilterArgs == null) {
            return findAllDto(pageable);
        } else if (sortAndFilterArgs.getFilterBy().isEmpty()) {
            return findAllDto(pageable);
        } else {
            return filterAndSort(sortAndFilterArgs, pageable);
        }
    }

    @Override
    public Page<AdDto> findByTagsName(String name, Pageable pageable) {
        Page<Ad> page = adRepository.findByTagsName(name, pageable);
        return page.map(adConverter::createFrom);
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
    public void save(AdDto adDto) {
        adRepository.save(adConverter.createFrom(adDto));
    }

    @Override
    public void save(Ad theAd) {
        adRepository.save(theAd);
    }

    @Override
    public void deleteById(int id) {
        adRepository.deleteById(id);
    }

    @Override
    public Ad publishNewAd(AdDto adDto, User user) throws JsonProcessingException {
        final Coordinates xy = CoordinatesUtil.translateAdressToCoordinates(adDto.getCity(), adDto.getStreet());

        Ad ad = adConverter.createFrom(adDto);
        ad.setAdAuthor(user);
        ad.setCoordinates(xy);
        return adRepository.save(ad);
    }

    private Page<AdDto> filterAndSort(SortAndFilterArguments sortAndFilterArgs, Pageable pageable) {
        Map<String, String> filterArgs = sortAndFilterArgs.getFilterBy();
        return findByTagsName(filterArgs.get("tag"), pageable);
    }

}
