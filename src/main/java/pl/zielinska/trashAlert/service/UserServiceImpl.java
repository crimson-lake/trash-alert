package pl.zielinska.trashAlert.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zielinska.trashAlert.dao.UserDAO;
import pl.zielinska.trashAlert.entity.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @NonNull
    private UserDAO userDAO;

    @Override
    @Transactional
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    @Transactional
    public void save(User theUser) {
        userDAO.save(theUser);
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        userDAO.deleteByUsername(username);
    }
}
