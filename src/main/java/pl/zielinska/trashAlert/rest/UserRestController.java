package pl.zielinska.trashAlert.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zielinska.trashAlert.dao.UserDAO;
import pl.zielinska.trashAlert.entity.User;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {

    @NonNull
    private UserDAO userDAO;

    @GetMapping("/users")
    public List<User> findAll() {
        return userDAO.findAll();
    }

}
