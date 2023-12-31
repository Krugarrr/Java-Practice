package pussy.protection.mapping;

import pussy.protection.dto.UserDto;
import pussy.protection.models.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapping {
    public static UserEntity FromDto(UserDto userDto) {
        return new UserEntity(userDto.getId(), userDto.getEmail(), userDto.getPassword(), userDto.getRole(), userDto.isTokenExpired());
    }

    public UserDto ToDto(UserEntity user) {
        return new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getRole(), user.isTokenExpired());
    }
}
