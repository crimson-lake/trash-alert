package pl.zielinska.trashAlert.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.trashAlert.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
