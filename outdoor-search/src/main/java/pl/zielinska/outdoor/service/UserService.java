package pl.zielinska.outdoor.service;

import pl.zielinska.outdoor.domain.Ad;
import pl.zielinska.outdoor.domain.User;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> findAll();
    User findByUsername(String username);
    User findByEmail(String email);
    void save(User theUser);
    void bindAdWithUser(User theUser, Ad theAd);
    Set<AdDto> usersAdsDto(String username);
    User registerNewUserAccount(UserDto userDto);
    boolean usernameAvailable(String name);
    boolean emailAvailable(String email);
}
