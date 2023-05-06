package gigachad.security.mapping;

import gigachad.security.dto.OwnerDto;
import gigachad.security.models.Owner;
import org.springframework.stereotype.Service;

@Service
public class OwnerMapping {
    public static Owner FromDto(OwnerDto ownerDto) {
        return new Owner(ownerDto.getId(), ownerDto.getName(), ownerDto.getBirthDate());
    }

    public OwnerDto ToDto(Owner owner) {
        return new OwnerDto(owner.getId(), owner.getName(), owner.getBirthDate());
    }
}
