package pl.zielinska.trashAlert.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.trashAlert.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
}
