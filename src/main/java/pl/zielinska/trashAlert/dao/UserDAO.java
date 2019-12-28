package pl.zielinska.trashAlert.dao;

import pl.zielinska.trashAlert.entity.User;

import java.util.List;

public interface UserDAO {

    public List<User> findAll();
}
