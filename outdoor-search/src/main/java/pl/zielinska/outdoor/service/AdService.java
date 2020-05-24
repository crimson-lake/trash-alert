package pl.zielinska.outdoor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.User;
import pl.zielinska.outdoor.dto.AdDto;

import java.util.List;

public interface AdService {

    List<Ad> findAll();
    List<AdDto> findAllDto();
    Ad findById(int id);
    AdDto findByIdDto(int id);
    void save(Ad theAd);
    Ad publishNewAd(AdDto adDto, User user) throws JsonProcessingException;
}
