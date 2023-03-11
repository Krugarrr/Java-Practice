package gigachad.pussy;

import gigachad.pussy.models.Breed;
import gigachad.pussy.models.Color;
import gigachad.pussy.models.Owner;
import gigachad.pussy.models.Pussy;
import gigachad.pussy.services.OwnerService;
import gigachad.pussy.services.PussyService;
import org.hibernate.Session;
import org.joda.time.MutableDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PussyTest {
    private OwnerService ownerService = mock(OwnerService.class);
    private PussyService pussyService = mock(PussyService.class);
    private Session session = mock(Session.class);

    @Test
    public void AddGetTest() {
        Pussy pussy = new Pussy(1, "pussyboy", MutableDateTime.now(), Breed.HORNY, Color.BLUE);
        Owner owner = new Owner(0, "Aedilay", MutableDateTime.now());

        pussyService.add(pussy);
        ownerService.add(owner);

        when(pussyService.getById(1)).thenReturn(pussy);
        when(ownerService.getById(0)).thenReturn(owner);
        Assertions.assertEquals("pussyboy", pussyService.getById(1).getName());
        Assertions.assertEquals("Aedilay", ownerService.getById(0).getName());
    }
}
