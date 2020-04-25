package pl.zielinska.trashAlert.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zielinska.trashAlert.dao.UserRepository;
import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.domain.User;
import pl.zielinska.trashAlert.dto.AdDto;
import pl.zielinska.trashAlert.dto.UserDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor @NoArgsConstructor
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
    public void bindAdWithUser(User theUser, Ad theAd) {
        theUser.addNewAd(theAd);
        save(theUser);
    }

    @Override
    public Set<AdDto> usersAdsDto(String username) {
        User user = userRepository.findByUsername(username);
        return user
                .getAds()
                .stream()
                .map(Ad::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public User registerNewUserAccount(UserDto userDto) {
        User userAccount = User.builder()
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .defaultCity(userDto.getDefaultCity())
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
