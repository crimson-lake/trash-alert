package pl.zielinska.outdoor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.model.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    Tag findByTag(String tag);
}
