package pl.zielinska.trashAlert.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zielinska.trashAlert.DTO.UserDto;
import pl.zielinska.trashAlert.dao.UserRepository;
import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.domain.User;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
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

    @Override
    public User registerNewUserAccount(UserDto userDto) {
        User userAccount = User.builder()
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .authority("USER")
                .enabled(true)
                .build();
        return userRepository.save(userAccount);
    }

    @Override
    public boolean usernameAvailable(String name) {
        if (findByUsername(name) == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean emailAvailable(String email) {
        if (findByEmail(email) == null) {
            return true;
        }
        return false;
    }


}
