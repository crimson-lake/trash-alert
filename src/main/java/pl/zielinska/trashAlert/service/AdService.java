package pl.zielinska.trashAlert.service;

import pl.zielinska.trashAlert.entity.Ad;

import java.util.List;

public interface AdService {

    List<Ad> findAll();
    Ad findByAuthor();
}
