package pl.zielinska.outdoor.service;

import pl.zielinska.model.domain.Photo;

import java.util.List;

public interface PhotoService {

    List<Photo> photosByAdId(int id);
    Photo photoById(int id);
    void deletePhotos(List<Integer> ids);
}
