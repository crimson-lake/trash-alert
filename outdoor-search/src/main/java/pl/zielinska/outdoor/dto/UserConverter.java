package pl.zielinska.outdoor.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.zielinska.model.domain.User;

@Component
public class UserConverter implements ConverterDto<User, UserDto> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createFrom(UserDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .defaultCity(dto.getDefaultCity())
                .password(passwordEncoder.encode(dto.getPassword()))
                .authority("USER")
                .enabled(true)
                .build();
    }

    @Override
    public UserDto createFrom(User entity) {
        return UserDto.builder()
                    .username(entity.getUsername())
                    .firstName(entity.getFirstName())
                    .lastName(entity.getLastName())
                    .email(entity.getEmail())
                    .defaultCity(entity.getDefaultCity())
                    .build();
    }

    @Override
    public User updateEntity(User entity, UserDto dto) {
        return null;
    }
}
