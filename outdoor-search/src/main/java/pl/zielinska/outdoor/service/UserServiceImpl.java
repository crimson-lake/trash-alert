package pl.zielinska.outdoor.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zielinska.outdoor.dao.UserRepository;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.User;
import pl.zielinska.outdoor.dto.*;
import pl.zielinska.outdoor.exception.NotFoundException;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor @NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConverterDto<Ad, AdDto> adConverter;

    @Autowired
    private ConverterDto<User, UserDto> userConverter;

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
    public List<AdDto> usersAdsDto(String username) {
        User user = userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new NotFoundException("User not found."));
        return adConverter.createFromEntities(user.getAds());
    }

    @Override
    public User registerNewUserAccount(UserDto userDto) {
        User user = userConverter.createFrom(userDto);
        user.setAuthority("USER");
        user.setEnabled(true);
        return userRepository.save(user);
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
