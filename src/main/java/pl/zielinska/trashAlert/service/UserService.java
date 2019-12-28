package pl.zielinska.trashAlert.service;

import pl.zielinska.trashAlert.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByUsername(String username);

    void save(User theUser);

    void deleteByUsername(String username);
}
