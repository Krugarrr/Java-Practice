package gigachad.springpussy.services;


import gigachad.springpussy.dao.OwnerDao;
import gigachad.springpussy.dao.PussyDao;
import gigachad.springpussy.dto.PussyDto;
import gigachad.springpussy.mapping.PussyMapping;
import gigachad.springpussy.models.Pussy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PussyServiceImpl implements PussyService {
    @Autowired
    private final PussyDao pussyDao;
    @Autowired
    private final PussyMapping pussyMapping;
    @Autowired private final OwnerDao ownerDao;

    @Autowired
    public PussyServiceImpl(PussyDao pussyDao, PussyMapping pussyMapping, OwnerDao ownerDao) {
        this.pussyDao = pussyDao;
        this.pussyMapping = pussyMapping;
        this.ownerDao = ownerDao;
    }

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
