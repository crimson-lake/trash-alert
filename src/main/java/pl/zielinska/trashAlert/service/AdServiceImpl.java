package pl.zielinska.trashAlert.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zielinska.trashAlert.dao.AdRepository;
import pl.zielinska.trashAlert.entity.Ad;

import java.util.List;

@Service
@AllArgsConstructor
public class AdServiceImpl implements AdService{

    @Autowired
    private AdRepository adRepository;

    @Override
    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    @Override
    public Ad findByAuthor() {
        return null;
    }
}
