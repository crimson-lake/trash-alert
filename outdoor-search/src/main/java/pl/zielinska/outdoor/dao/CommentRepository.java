package pl.zielinska.outdoor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.outdoor.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}