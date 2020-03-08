package pl.zielinska.trashAlert.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.trashAlert.domain.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
}
