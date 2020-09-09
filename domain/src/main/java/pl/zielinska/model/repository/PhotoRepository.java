package pl.zielinska.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.model.domain.Photo;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    List<Photo> findByAdId(int id);
    Photo findById(int id);
}
