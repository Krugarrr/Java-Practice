package gigachad.pussy.mapping;

import gigachad.pussy.dto.OwnerDto;
import gigachad.pussy.models.Owner;

public class OwnerMapping {
    public static Owner OwnerMappingDto (OwnerDto ownerDto) {
        return new Owner(ownerDto.getId(), ownerDto.getName(), ownerDto.getBirthDate());
    }
}
