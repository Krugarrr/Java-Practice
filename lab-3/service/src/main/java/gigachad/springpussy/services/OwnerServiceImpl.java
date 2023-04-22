package gigachad.springpussy.services;


import gigachad.springpussy.dao.OwnerDao;
import gigachad.springpussy.dto.OwnerDto;
import gigachad.springpussy.dto.PussyDto;
import gigachad.springpussy.mapping.OwnerMapping;
import gigachad.springpussy.mapping.PussyMapping;
import gigachad.springpussy.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerDao ownerDao;
    private final OwnerMapping ownerMapping;
    private final PussyMapping pussyMapping;

    @Autowired
    public OwnerServiceImpl(OwnerDao ownerDao, OwnerMapping ownerMapping, PussyMapping pussyMapping) {
        this.ownerDao = ownerDao;
        this.ownerMapping = ownerMapping;
        this.pussyMapping = pussyMapping;
    }

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
