package gigachad.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import gigachad.security.dao.UserDao;
import gigachad.security.dto.PussyDto;
import gigachad.security.dto.UserDto;
import gigachad.security.jwt.JwtDecoder;
import gigachad.security.jwt.JwtIssuer;
import gigachad.security.jwt.JwtProperties;
import gigachad.security.mapping.UserMapping;
import gigachad.security.models.UserEntity;
import gigachad.security.securities.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private static final String EXISTING_EMAIL = "test@test.com";
    public UserDto findByEmail(String email) {
        var user = userDao.findByEmail(email);
        if (! EXISTING_EMAIL.equalsIgnoreCase(user.getEmail())) return null;
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
