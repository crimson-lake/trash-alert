package pl.zielinska.outdoor.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.zielinska.outdoor.domain.Ad;
import pl.zielinska.outdoor.domain.User;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.dto.UserDto;
import pl.zielinska.outdoor.service.AdService;
import pl.zielinska.outdoor.service.GeoService;
import pl.zielinska.outdoor.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdService adService;

    @Autowired
    private GeoService geoService;

    @GetMapping
    public List<UserDto> findAll() {
        return userService
                .findAll()
                .stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{username}")
    public UserDto find(@PathVariable("username") String username) {
        return userService.findByUsername(username).toDto();
    }

    @GetMapping(path = "/{username}/ads")
    public Set<AdDto> usersAds(@PathVariable("username") String username) {
        return userService.usersAdsDto(username);
    }

    @PostMapping
    public User addUser(@RequestBody UserDto theUser) {
        return userService.registerNewUserAccount(theUser);
    }

    @PostMapping(path = "/{username}/ad-new")
    public Ad addNewAd(@RequestBody AdDto adDto, @PathVariable("username") String username) throws JsonProcessingException {
        User theUser = userService.findByUsername(username);
        Ad theAd = adService.publishNewAd(adDto, theUser);

        userService.bindAdWithUser(theUser, theAd);
        return theAd;
    }

}
