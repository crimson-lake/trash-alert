package pl.zielinska.outdoor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.outdoor.domain.Ad;
import pl.zielinska.outdoor.domain.User;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer> {

    List<Ad> findByAdAuthor(User theUser);
}
