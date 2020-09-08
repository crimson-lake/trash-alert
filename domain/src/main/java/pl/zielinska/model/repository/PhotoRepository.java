package pl.zielinska.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.model.domain.Photo;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    Optional<Photo> findByAdId(int id);
}
