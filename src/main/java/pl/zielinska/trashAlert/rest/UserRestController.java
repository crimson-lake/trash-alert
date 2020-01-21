package pl.zielinska.trashAlert.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.zielinska.trashAlert.entity.User;
import pl.zielinska.trashAlert.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE})
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE})
    public User findUser(@RequestParam String username) {
        return userService.findByUsername(username);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,
                             MediaType.APPLICATION_JSON_VALUE})
    public User addUser(@RequestBody User theUser) {
        theUser.setId(0);
        userService.save(theUser);
        return theUser;
    }

    @PutMapping(consumes = {MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE})
    public User updateUser(@RequestBody User theUser) {
        userService.save(theUser);
        return theUser;
    }

}
