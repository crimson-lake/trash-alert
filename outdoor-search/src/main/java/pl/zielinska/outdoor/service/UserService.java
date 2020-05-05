package pl.zielinska.outdoor.service;

import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.User;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.dto.UserDto;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findByUsername(String username);
    User findByEmail(String email);
    void save(User theUser);
    void bindAdWithUser(User theUser, Ad theAd);
    List<AdDto> usersAdsDto(String username);
    User registerNewUserAccount(UserDto userDto);
    boolean usernameAvailable(String name);
    boolean emailAvailable(String email);
}
