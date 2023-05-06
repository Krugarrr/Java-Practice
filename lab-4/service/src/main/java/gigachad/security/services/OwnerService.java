package gigachad.security.services;


import gigachad.security.dto.OwnerDto;
import gigachad.security.dto.PussyDto;

import java.util.List;

public interface OwnerService {
    List<OwnerDto> allOwners();
    void add(OwnerDto ownerDto);
    void delete(OwnerDto ownerDto);
    void change(OwnerDto ownerDto);
    OwnerDto getById(long id) throws Exception;
    List<PussyDto> getPussies(long ownerId);
}
