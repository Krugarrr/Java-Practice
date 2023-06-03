package micro.pussy.services;


import lombok.RequiredArgsConstructor;
import micro.pussy.mapping.OwnerMapping;
import micro.pussy.models.Owner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import micro.pussy.dao.OwnerDao;
import micro.pussy.dto.OwnerDto;
import java.util.stream.Collectors;

@Service("owner-service")
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerDao ownerDao;
    private final OwnerMapping ownerMapping;
    private static final Logger LOGGER = LoggerFactory.getLogger(OwnerServiceImpl.class);

    @KafkaListener(topics = "ownerTopic_json", groupId = "myGroup")
    public List<OwnerDto> allOwners() {
        var owners = ownerDao.findAll().stream().map(ownerMapping::ToDto).collect(Collectors.toList());
        LOGGER.info(String.format("Message received GET ->%s", owners));
        return owners;
    }

    @KafkaListener(topics = "ownerTopic_json", groupId = "myGroup")
    public void add(OwnerDto ownerDto) {
        LOGGER.info(String.format("Message received POST ->%s", ownerDto.toString()));
        Owner owner = OwnerMapping.FromDto(ownerDto);
        ownerDao.save(owner);
    }

    @KafkaListener(topics = "ownerTopic_json", groupId = "myGroup")
    public void delete(OwnerDto ownerDto) {
        LOGGER.info(String.format("Message received DELETE ->%s", ownerDto.toString()));
        Owner owner = OwnerMapping.FromDto(ownerDto);
        ownerDao.delete(owner);
    }

    @KafkaListener(topics = "ownerTopic_json", groupId = "myGroup")
    public void change(OwnerDto ownerDto) {
        LOGGER.info(String.format("Message received UPDATE ->%s", ownerDto.toString()));
        Owner owner = OwnerMapping.FromDto(ownerDto);
        ownerDao.save(owner);
    }

    @KafkaListener(topics = "ownerTopic_json", groupId = "myGroup")
    public OwnerDto getById(long id) throws Exception {
        if (ownerDao.findById(id).isEmpty()) {
            throw new Exception();
        }
        var owner = ownerMapping.ToDto(ownerDao.findById(id).get());
        LOGGER.info(String.format("Message received GET ->%s", owner));
        return owner;
    }
}
