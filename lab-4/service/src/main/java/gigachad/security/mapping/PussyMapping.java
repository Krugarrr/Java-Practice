package gigachad.security.mapping;

import gigachad.security.dto.OwnerDto;
import gigachad.security.dto.PussyDto;
import gigachad.security.models.Pussy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PussyMapping {
    private final OwnerMapping ownerMapping;

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
