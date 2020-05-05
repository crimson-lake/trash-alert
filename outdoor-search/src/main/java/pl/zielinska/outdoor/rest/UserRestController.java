package pl.zielinska.outdoor.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.User;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.dto.ConverterDto;
import pl.zielinska.outdoor.dto.UserDto;
import pl.zielinska.outdoor.service.AdService;
import pl.zielinska.outdoor.service.GeoService;
import pl.zielinska.outdoor.service.UserService;

import java.util.List;

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

    @Autowired
    private ConverterDto<User, UserDto> userConverter;

    @GetMapping
    public List<UserDto> findAll() {
        return userConverter.createFromEntities(userService.findAll());
    }

    @GetMapping(path = "/{username}")
    public UserDto find(@PathVariable("username") String username) {
        return userConverter.createFrom(userService.findByUsername(username));
    }

    @GetMapping(path = "/{username}/ads")
    public List<AdDto> usersAds(@PathVariable("username") String username) {
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
