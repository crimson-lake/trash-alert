package pl.zielinska.trashAlert.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.trashAlert.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
