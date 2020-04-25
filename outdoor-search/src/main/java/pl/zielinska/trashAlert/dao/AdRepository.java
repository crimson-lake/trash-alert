package pl.zielinska.trashAlert.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.domain.User;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer> {

    List<Ad> findByAdAuthor(User theUser);
}
