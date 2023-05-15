package pussy.protection.services;


import pussy.protection.dto.OwnerDto;
import pussy.protection.dto.PussyDto;

import java.util.List;

public interface OwnerService {
    List<OwnerDto> allOwners();
    void add(OwnerDto ownerDto);
    void delete(OwnerDto ownerDto);
    void change(OwnerDto ownerDto);
    OwnerDto getById(long id) throws Exception;
    List<PussyDto> getPussies(long ownerId);
}
