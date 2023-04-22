package gigachad.springpussy.services;


import gigachad.springpussy.dto.OwnerDto;
import gigachad.springpussy.dto.PussyDto;

import java.util.List;

public interface OwnerService {
    List<OwnerDto> allOwners();
    void add(OwnerDto ownerDto);
    void delete(OwnerDto ownerDto);
    void change(OwnerDto ownerDto);
    OwnerDto getById(long id) throws Exception;
    List<PussyDto> getPussies(long ownerId);
}
