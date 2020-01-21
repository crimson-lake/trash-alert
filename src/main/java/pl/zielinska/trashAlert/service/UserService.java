package pl.zielinska.trashAlert.service;

import pl.zielinska.trashAlert.entity.Ad;
import pl.zielinska.trashAlert.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> findAll();
    User findByUsername(String username);
    void save(User theUser);
    Set<Ad> usersAds(String username);
}
