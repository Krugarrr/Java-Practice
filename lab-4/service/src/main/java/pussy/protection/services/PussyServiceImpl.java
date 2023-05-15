package pussy.protection.services;


import pussy.protection.dao.OwnerDao;
import pussy.protection.dao.PussyDao;
import pussy.protection.dto.PussyDto;
import pussy.protection.mapping.PussyMapping;
import pussy.protection.models.Pussy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PussyServiceImpl implements PussyService {

    private final PussyDao pussyDao;
    private final PussyMapping pussyMapping;
    private final OwnerDao ownerDao;

    public List<PussyDto> getAll() {
        return pussyDao.findAll().stream().map(pussyMapping::ToDto).collect(Collectors.toList());
    }

    public List<PussyDto> getFriends(long pussyId) {
        var pussy = pussyDao.findById(pussyId).orElse(null);
        return pussy.getFriends().stream().map(pussyMapping::ToDto).collect(Collectors.toList());
    }

    public void add(PussyDto pussyDto) {
        Pussy pussy = PussyMapping.FromDto(pussyDto);
        pussyDao.save(pussy);
    }

    public void destroyPussy(PussyDto pussyDto) {
        Pussy pussy = PussyMapping.FromDto(pussyDto);
        pussyDao.delete(pussy);
    }

    public void change(PussyDto pussyDto) {
        Pussy pussy = PussyMapping.FromDto(pussyDto);
        pussyDao.save(pussy);
    }

    public PussyDto findById(long id) throws Exception {
        return pussyMapping.ToDto(pussyDao.findById(id).orElse(new Pussy()));
    }

    public void setOwner(long pussyId, long ownerId) throws Exception {
        var pussy = pussyDao.findById(pussyId).orElse(null);
        var owner = ownerDao.findById(ownerId).orElse(null);
        pussy.setOwner(owner);
        owner.getPussies().add(pussy);
        pussyDao.save(pussy);
        ownerDao.save(owner);
    }

    public void addFriend(long firstPussyId, long secondPussyId) {
        var firstPussy = pussyDao.findById(firstPussyId).orElse(null);
        var secondPussy = pussyDao.findById(secondPussyId).orElse(null);
        firstPussy.getFriends().add(secondPussy);
        secondPussy.getFriends().add(firstPussy);
        pussyDao.save(firstPussy);
        pussyDao.save(secondPussy);
    }
}
