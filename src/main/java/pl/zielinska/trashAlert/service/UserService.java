package pl.zielinska.trashAlert.service;

import pl.zielinska.trashAlert.DTO.UserDto;
import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.domain.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> findAll();
    User findByUsername(String username);
    void save(User theUser);
    Set<Ad> usersAds(String username);
    User registerNewUserAccount(UserDto userDto);
}
