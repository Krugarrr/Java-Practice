package pussy.protection.services;

import pussy.protection.dao.UserDao;
import pussy.protection.dto.UserDto;
import pussy.protection.mapping.UserMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserMapping userMapping;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserDto findByEmail(String email) {
        var user = userDao.findByEmail(email);
        if (! user.getEmail().equalsIgnoreCase(user.getEmail())) return null;
        return userMapping.ToDto(user);
    }
    public void addUser(UserDto userDto) {
        var user = UserMapping.FromDto(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    public List<UserDto> getAll() {
        return userDao.findAll().stream().map(userMapping::ToDto).collect(Collectors.toList());
    }
}
