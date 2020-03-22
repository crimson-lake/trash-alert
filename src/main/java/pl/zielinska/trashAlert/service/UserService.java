package pl.zielinska.trashAlert.service;

import pl.zielinska.trashAlert.DTO.UserDto;
import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.domain.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> findAll();
    User findByUsername(String username);
    User findByEmail(String email);
    void save(User theUser);
    void bindAdWithUser(User theUser, Ad theAd);
    Set<Ad> usersAds(String username);
    User registerNewUserAccount(UserDto userDto);
    boolean usernameAvailable(String name);
    boolean emailAvailable(String email);
}
