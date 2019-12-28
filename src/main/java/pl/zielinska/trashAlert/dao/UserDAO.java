package pl.zielinska.trashAlert.dao;

import pl.zielinska.trashAlert.entity.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User findByUsername(String username);

    void save(User theUser);

    void deleteByUsername(String username);
}
