package micro.pussy.mapping;

import micro.pussy.dto.OwnerDto;
import micro.pussy.models.Owner;
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
