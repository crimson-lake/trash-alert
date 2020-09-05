package pl.zielinska.outdoor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.SortAndFilterArguments;
import pl.zielinska.model.domain.User;
import pl.zielinska.outdoor.dto.AdDto;

public interface AdService {

    Page<Ad> findAll(Pageable pageable);
    Page<AdDto> findAllDto(Pageable pageable);
    Page<AdDto> findAllDto(SortAndFilterArguments sortAndFilterArgs, Pageable pageable);
    Page<AdDto> findByTagsName(String name, Pageable pageable);
    Ad findById(int id);
    AdDto findByIdDto(int id);
    void save(AdDto adDto);
    void save(Ad theAd);
    void deleteById(int id);
    Ad publishNewAd(AdDto adDto, User user) throws JsonProcessingException;
}
