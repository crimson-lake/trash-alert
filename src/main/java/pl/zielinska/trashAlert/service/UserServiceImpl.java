package pl.zielinska.trashAlert.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zielinska.trashAlert.dao.UserRepository;
import pl.zielinska.trashAlert.entity.Ad;
import pl.zielinska.trashAlert.entity.User;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User theUser) {
        userRepository.save(theUser);
    }

    @Override
    public Set<Ad> usersAds(String username) {
        User user = userRepository.findByUsername(username);
        return user.getAds();
    }
}
