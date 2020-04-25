package pl.zielinska.trashAlert.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.trashAlert.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    Tag findByTag(String tag);
}
