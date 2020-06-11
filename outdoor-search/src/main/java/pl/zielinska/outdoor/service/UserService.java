package pl.zielinska.outdoor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.User;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.dto.LocationDto;
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
    void addNewLocationToUser(User user, LocationDto locationDto) throws JsonProcessingException;
    boolean usernameAvailable(String name);
    boolean emailAvailable(String email);
}
