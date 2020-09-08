package pl.zielinska.outdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zielinska.model.domain.Photo;
import pl.zielinska.model.repository.PhotoRepository;

import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Optional<Photo> photoByAdId(int id) {
        return photoRepository.findByAdId(id);
    }
}
