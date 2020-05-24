package pl.zielinska.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.User;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer> {

    List<Ad> findByAdAuthor(User theUser);
}
