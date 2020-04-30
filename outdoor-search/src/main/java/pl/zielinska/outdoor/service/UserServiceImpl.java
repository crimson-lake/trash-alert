package pl.zielinska.outdoor.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zielinska.outdoor.dao.UserRepository;
import pl.zielinska.outdoor.domain.Ad;
import pl.zielinska.outdoor.domain.User;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.dto.UserDto;
import pl.zielinska.outdoor.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
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
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found."));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found."));
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
        User user = userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new NotFoundException("User not found."));
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
        if (userRepository.findByUsername(name).isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean emailAvailable(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return false;
        }
        return true;
    }


}
