package pl.zielinska.trashAlert.service;

import pl.zielinska.trashAlert.domain.Ad;

import java.util.List;

public interface AdService {

    List<Ad> findAll();
    Ad findByAuthor();
    Ad findById(int id);
    void save(Ad theAd);
}
