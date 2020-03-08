package pl.zielinska.trashAlert.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.trashAlert.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
