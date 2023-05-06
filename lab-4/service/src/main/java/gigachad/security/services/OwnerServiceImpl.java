package gigachad.security.services;


import gigachad.security.dao.OwnerDao;
import gigachad.security.dto.OwnerDto;
import gigachad.security.dto.PussyDto;
import gigachad.security.mapping.OwnerMapping;
import gigachad.security.mapping.PussyMapping;
import gigachad.security.models.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerDao ownerDao;
    private final OwnerMapping ownerMapping;
    private final PussyMapping pussyMapping;

    public List<OwnerDto> allOwners() {
        return ownerDao.findAll().stream().map(ownerMapping::ToDto).collect(Collectors.toList());
    }

    public List<PussyDto> getPussies(long ownerId) {
        var owner = ownerDao.findById(ownerId).orElseThrow();
        return owner.getPussies().stream().map(pussyMapping::ToDto).collect(Collectors.toList());
    }

    public void add(OwnerDto ownerDto) {
        Owner owner = OwnerMapping.FromDto(ownerDto);
        ownerDao.save(owner);
    }

    public void delete(OwnerDto ownerDto) {
        Owner owner = OwnerMapping.FromDto(ownerDto);
        ownerDao.delete(owner);
    }

    public void change(OwnerDto ownerDto) {
        Owner owner = OwnerMapping.FromDto(ownerDto);
        ownerDao.save(owner);
    }

    public OwnerDto getById(long id) throws Exception {
        if (ownerDao.findById(id).isEmpty()) {
            throw new Exception();
        }
        return ownerMapping.ToDto(ownerDao.findById(id).get());
    }
}
