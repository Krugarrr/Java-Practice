package gigachad.pussy.controllers;

import gigachad.pussy.dto.OwnerDto;
import gigachad.pussy.dto.PussyDto;
import gigachad.pussy.mapping.OwnerMapping;
import gigachad.pussy.mapping.PussyMapping;
import gigachad.pussy.models.Owner;
import gigachad.pussy.models.Pussy;
import gigachad.pussy.services.PussyService;
import gigachad.pussy.services.PussyServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class PussyController {
    private PussyService pussyService = new PussyServiceImpl();
    public List<PussyDto> getPussies() {
        List<PussyDto> pussyDto = new ArrayList<>();
        List<Pussy> pussies = pussyService.allPussies();

        for (Pussy puss : pussies) {
            pussyDto.add(new PussyDto(puss.getNumber(), puss.getName(), puss.getBirthDate(), puss.getBreed(), puss.getColor()));
        }

        return pussyDto;
    }

    public void addPussy(PussyDto pussyDto) {
        Pussy pussy = PussyMapping.PussyMappingDto(pussyDto);
        pussyService.add(pussy);
    }
    public void destroyPussy(PussyDto pussyDto) {
        Pussy pussy = PussyMapping.PussyMappingDto(pussyDto);
        pussyService.destroyPussy(pussy);
    }
    public void changePussy(PussyDto pussyDto) {
        Pussy pussy = PussyMapping.PussyMappingDto(pussyDto);
        pussyService.change(pussy);
    }
    public PussyDto getById(long id) {
        Pussy puss = pussyService.getById(id);
        return new PussyDto(puss.getNumber(), puss.getName(), puss.getBirthDate(), puss.getBreed(), puss.getColor());
    }

    public void setOwner(PussyDto pussyDto, OwnerDto ownerDto) {
        Pussy pussy = PussyMapping.PussyMappingDto(pussyDto);
        Owner owner = OwnerMapping.OwnerMappingDto(ownerDto);
        pussyService.setOwner(pussy, owner);
    }
    public void addFriend(PussyDto pussyDto1, PussyDto pussyDto2) {
        Pussy pussy1 = PussyMapping.PussyMappingDto(pussyDto1);
        Pussy pussy2 = PussyMapping.PussyMappingDto(pussyDto2);
        pussyService.addFriend(pussy1, pussy2);
    }
}
