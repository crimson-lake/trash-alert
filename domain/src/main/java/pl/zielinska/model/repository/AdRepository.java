package pl.zielinska.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.User;

import java.util.List;

public interface AdRepository extends PagingAndSortingRepository<Ad, Integer> {

    List<Ad> findAll();
    Page<Ad> findAll(Pageable pageable);
    List<Ad> findByAdAuthor(User theUser);
    Page<Ad> findByTagsName(String tag, Pageable pageable);
}
