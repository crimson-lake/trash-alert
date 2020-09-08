package pl.zielinska.outdoor.service;

import pl.zielinska.model.domain.Photo;

import java.util.Optional;

public interface PhotoService {

    Optional<Photo> photoByAdId(int id);
}
