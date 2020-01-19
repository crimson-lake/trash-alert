package pl.zielinska.trashAlert.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.zielinska.trashAlert.entity.Ad;
import pl.zielinska.trashAlert.entity.User;
import pl.zielinska.trashAlert.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {

    @NonNull
    private UserService userService;

    @GetMapping(path="/users",
                produces = {MediaType.APPLICATION_XML_VALUE})
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User theUser) {
        theUser.setId(0);
        userService.save(theUser);
        return theUser;
    }

    @GetMapping(path="/ads",
                produces = {MediaType.APPLICATION_XML_VALUE})
    public Set<Ad> usersAds() {
        return userService.usersAds();
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User theUser) {
        userService.save(theUser);
        return theUser;
    }


}
