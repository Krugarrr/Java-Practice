package gigachad.pussy.controllers;

import gigachad.pussy.dto.OwnerDto;
import gigachad.pussy.dto.PussyDto;
import gigachad.pussy.mapping.OwnerMapping;
import gigachad.pussy.mapping.PussyMapping;
import gigachad.pussy.models.Owner;
import gigachad.pussy.models.Pussy;
import gigachad.pussy.services.OwnerService;
import gigachad.pussy.services.OwnerServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class OwnerController {
    private OwnerService ownerService = new OwnerServiceImpl();
    public List<OwnerDto> getOwners() {
        List<OwnerDto> ownerDto = new ArrayList<>();
        List<Owner> owners = ownerService.allOwners();

        for (Owner owner : owners) {
            ownerDto.add(new OwnerDto(owner.getName(), owner.getBirthDate()));
        }
        return ownerDto;
    }

    public void addOwner(OwnerDto ownerDto) {
        Owner owner = OwnerMapping.OwnerMappingDto(ownerDto);
        ownerService.add(owner);
    }
    public void deleteOwner(OwnerDto ownerDto) {
        Owner owner = OwnerMapping.OwnerMappingDto(ownerDto);
        ownerService.delete(owner);
    }
    public void changeOwner(OwnerDto ownerDto) {
        Owner owner = OwnerMapping.OwnerMappingDto(ownerDto);
        ownerService.change(owner);
    }
    public OwnerDto getById(int id) {
        Owner owner = ownerService.getById(id);
        return new OwnerDto(owner.getName(), owner.getBirthDate());
    }
}
