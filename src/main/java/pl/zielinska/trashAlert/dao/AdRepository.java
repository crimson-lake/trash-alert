package pl.zielinska.trashAlert.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zielinska.trashAlert.entity.Ad;

public interface AdRepository extends JpaRepository<Ad, Integer> {
}
