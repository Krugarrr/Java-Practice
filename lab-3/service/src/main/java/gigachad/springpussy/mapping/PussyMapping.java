package gigachad.springpussy.mapping;

import gigachad.springpussy.dto.OwnerDto;
import gigachad.springpussy.dto.PussyDto;
import gigachad.springpussy.models.Pussy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PussyMapping {
    private final OwnerMapping ownerMapping;

    @Autowired
    public PussyMapping(OwnerMapping ownerMapping) {
        this.ownerMapping = ownerMapping;
    }

    public static Pussy FromDto(PussyDto pussyDto) {
        return new Pussy(pussyDto.getNumber(), pussyDto.getName(), pussyDto.getBirthDate(), pussyDto.getBreed(), pussyDto.getColor());
    }

    public PussyDto ToDto(Pussy pussy) {
        PussyDto pussyDto = new PussyDto();
        pussyDto.setNumber(pussy.getNumber());
        pussyDto.setName(pussy.getName());
        pussyDto.setBirthDate(pussy.getBirthDate());
        pussyDto.setBreed(pussy.getBreed());
        pussyDto.setColor(pussy.getColor());
        if (pussy.getOwner() == null) {
            pussyDto.setOwner(new OwnerDto());
        }
        else {
            pussyDto.setOwner(ownerMapping.ToDto(pussy.getOwner()));
        }
        return pussyDto;
    }
}
