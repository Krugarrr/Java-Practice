package micro.pussy.services;


import java.util.List;
import micro.pussy.dto.OwnerDto;

public interface OwnerService {
    List<OwnerDto> allOwners();
    void add(OwnerDto ownerDto);
    void delete(OwnerDto ownerDto);
    void change(OwnerDto ownerDto);
    OwnerDto getById(long id) throws Exception;
}
