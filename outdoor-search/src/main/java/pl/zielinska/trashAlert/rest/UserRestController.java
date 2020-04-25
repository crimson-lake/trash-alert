package pl.zielinska.trashAlert.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.domain.User;
import pl.zielinska.trashAlert.dto.AdDto;
import pl.zielinska.trashAlert.dto.UserDto;
import pl.zielinska.trashAlert.service.AdService;
import pl.zielinska.trashAlert.service.GeoService;
import pl.zielinska.trashAlert.service.UserService;

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
    public Ad addNewAd(@RequestBody AdDto adDto, @PathVariable("username") String username) {
        User theUser = userService.findByUsername(username);
        Ad theAd = adService.publishNewAd(adDto, theUser);

        userService.bindAdWithUser(theUser, theAd);
        geoService.adNewCoordinates(theAd);
        return theAd;
    }

}
