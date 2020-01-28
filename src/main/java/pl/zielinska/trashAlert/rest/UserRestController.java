package pl.zielinska.trashAlert.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.zielinska.trashAlert.entity.Ad;
import pl.zielinska.trashAlert.entity.User;
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

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE})
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = "/{username}",
                produces = {MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE})
    public User find(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }

    @GetMapping(path = "/{username}/ads",
                produces = {MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE})
    public Set<Ad> usersAds(@PathVariable("username") String username) {
        return userService.usersAds(username);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
                             MediaType.APPLICATION_XML_VALUE})
    public User addUser(@RequestBody User theUser) {
        theUser.setId(0);
        userService.save(theUser);
        return theUser;
    }

    @PostMapping(path = "/{username}/ad-new")
    public Ad addNewAd(@RequestBody Ad theAd, @PathVariable("username") String username) {
        User theUser = userService.findByUsername(username);
        theAd.setId(0);
        theUser.addNewAd(theAd);
        userService.save(theUser);
        return theAd;
    }

    @PutMapping(consumes = {MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE})
    public User updateUser(@RequestBody User theUser) {
        userService.save(theUser);
        return theUser;
    }

}
