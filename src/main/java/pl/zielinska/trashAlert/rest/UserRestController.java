package pl.zielinska.trashAlert.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.zielinska.trashAlert.entity.User;
import pl.zielinska.trashAlert.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {

    @NonNull
    private UserService userService;

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{username}")
    public User find(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User theUser) {
        theUser.setId(0);
        userService.save(theUser);
        return theUser;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User theUser) {
        userService.save(theUser);
        return theUser;
    }

    @DeleteMapping("/users/username")
    public String deleteUser(@PathVariable String username) {
        userService.deleteByUsername(username);
        return username;
    }

}
