package pl.zielinska.outdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zielinska.model.domain.Photo;
import pl.zielinska.model.repository.PhotoRepository;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<Photo> photosByAdId(int id) {
        return photoRepository.findByAdId(id);
    }

    @Override
    public Photo photoById(int id) {
        return photoRepository.findById(id);
    }

    @Override
    public void deletePhotos(List<Integer> ids) {
        if (ids != null) {
            ids.stream().forEach(photoRepository::deleteById);
        }
    }


}
