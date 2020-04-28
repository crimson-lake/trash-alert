package pl.zielinska.outdoor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.zielinska.outdoor.domain.Ad;
import pl.zielinska.outdoor.domain.User;
import pl.zielinska.outdoor.dto.AdDto;

import java.util.List;

public interface AdService {

    List<Ad> findAll();
    List<AdDto> findAllDto();
    Ad findById(int id);
    void save(Ad theAd);
    Ad publishNewAd(AdDto adDto, User user) throws JsonProcessingException;
}
