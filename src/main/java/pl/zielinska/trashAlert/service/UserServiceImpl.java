package pl.zielinska.trashAlert.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zielinska.trashAlert.dao.AdRepository;
import pl.zielinska.trashAlert.dao.UserRepository;
import pl.zielinska.trashAlert.entity.Ad;
import pl.zielinska.trashAlert.entity.User;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User theUser) {
        userRepository.save(theUser);
    }

    public Set<Ad> usersAds() {
        User user = userRepository.findById(1).get();
        return user.getAds();
    }
}
