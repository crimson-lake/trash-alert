package pl.zielinska.trashAlert.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.zielinska.trashAlert.DTO.UserDto;
import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.domain.User;
import pl.zielinska.trashAlert.service.UserService;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = "/{username}")
    public User find(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }

    @GetMapping(path = "/{username}/ads")
    public Set<Ad> usersAds(@PathVariable("username") String username) {
        return userService.usersAds(username);
    }

    @PostMapping
    public User addUser(@RequestBody UserDto theUser) {
        return userService.registerNewUserAccount(theUser);
    }

    @PostMapping(path = "/{username}/ad-new")
    public Ad addNewAd(@RequestBody Ad theAd, @PathVariable("username") String username) {
        User theUser = userService.findByUsername(username);
        theAd.setId(0);
        theUser.addNewAd(theAd);
        userService.save(theUser);
        return theAd;
    }

    @PutMapping
    public User updateUser(@RequestBody User theUser) {
        userService.save(theUser);
        return theUser;
    }

}
