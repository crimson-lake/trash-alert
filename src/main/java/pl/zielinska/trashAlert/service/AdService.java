package pl.zielinska.trashAlert.service;

import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.domain.User;
import pl.zielinska.trashAlert.dto.AdDto;

import java.util.List;

public interface AdService {

    List<Ad> findAll();
    Ad findByAuthor();
    Ad findById(int id);
    void save(Ad theAd);
    Ad publishNewAd(AdDto adDto, User user);
}
