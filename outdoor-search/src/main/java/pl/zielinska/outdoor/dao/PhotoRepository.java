package pl.zielinska.outdoor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.outdoor.domain.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
}
