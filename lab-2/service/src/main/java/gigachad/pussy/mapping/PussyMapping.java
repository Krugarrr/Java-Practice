package gigachad.pussy.mapping;

import gigachad.pussy.dto.PussyDto;
import gigachad.pussy.models.Pussy;

public class PussyMapping {
    public static Pussy PussyMappingDto (PussyDto pussyDto) {
        return new Pussy(pussyDto.getName(), pussyDto.getBirthDate(), pussyDto.getBreed(), pussyDto.getColor());
    }
}
