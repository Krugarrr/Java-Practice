package gigachad.pussy.controllers;

import gigachad.pussy.dto.PussyDto;
import gigachad.pussy.mapping.PussyMapping;
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
            pussyDto.add(new PussyDto(puss.getName(), puss.getBirthDate(), puss.getBreed(), puss.getColor()));
        }

        return pussyDto;
    }

    public void addPussy(PussyDto pussyDto) {
        Pussy pussy = PussyMapping.PussyMappingDto(pussyDto);
        pussyService.add(pussy);
    }
    public void deletePussy(PussyDto pussyDto) {
        Pussy pussy = PussyMapping.PussyMappingDto(pussyDto);
        pussyService.delete(pussy);
    }
    public void changePussy(PussyDto pussyDto) {
        Pussy pussy = PussyMapping.PussyMappingDto(pussyDto);
        pussyService.change(pussy);
    }
    public PussyDto getById(int id) {
        Pussy puss = pussyService.getById(id);
        return new PussyDto(puss.getName(), puss.getBirthDate(), puss.getBreed(), puss.getColor());
    }
}
