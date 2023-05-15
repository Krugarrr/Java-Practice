package pussy.protection.mapping;

import pussy.protection.dto.OwnerDto;
import pussy.protection.models.Owner;
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
