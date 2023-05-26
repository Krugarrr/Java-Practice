package micro.pussy.services;



import lombok.RequiredArgsConstructor;
import micro.pussy.dao.PussyDao;
import micro.pussy.dto.PussyDto;
import micro.pussy.mapping.PussyMapping;
import micro.pussy.models.Pussy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PussyServiceImpl implements PussyService {

    private final PussyDao pussyDao;
    private final PussyMapping pussyMapping;
    private static final Logger LOGGER = LoggerFactory.getLogger(PussyServiceImpl.class);

    @KafkaListener(topics = "pussyTopic_json", groupId = "anotherGroup")
    public List<PussyDto> getAll() {
        var pussies = pussyDao.findAll().stream().map(pussyMapping::ToDto).collect(Collectors.toList());
        LOGGER.info(String.format("Message received GET ->%s", pussies));
        return pussies;
    }

    @KafkaListener(topics = "pussyTopic_json", groupId = "anotherGroup")
    public List<PussyDto> getFriends(long pussyId) {
        var pussy = pussyDao.findById(pussyId).orElse(null);
        var pussies = pussy.getFriends().stream().map(pussyMapping::ToDto).collect(Collectors.toList());
        LOGGER.info(String.format("Message received GET ->%s", pussies));
        return pussies;
    }

    @KafkaListener(topics = "pussyTopic_json", groupId = "anotherGroup")
    public void add(PussyDto pussyDto) {
        Pussy pussy = PussyMapping.FromDto(pussyDto);
        pussyDao.save(pussy);
        LOGGER.info(String.format("Message received POST ->%s", pussyDto));
    }

    @KafkaListener(topics = "pussyTopic_json", groupId = "anotherGroup")
    public void destroyPussy(PussyDto pussyDto) {
        Pussy pussy = PussyMapping.FromDto(pussyDto);
        pussyDao.delete(pussy);
        LOGGER.info(String.format("Message received DELETE ->%s", pussyDto));
    }

    @KafkaListener(topics = "pussyTopic_json", groupId = "anotherGroup")
    public void change(PussyDto pussyDto) {
        Pussy pussy = PussyMapping.FromDto(pussyDto);
        pussyDao.save(pussy);
        LOGGER.info(String.format("Message received UPDATE ->%s", pussyDto));
    }

    @KafkaListener(topics = "pussyTopic_json", groupId = "anotherGroup")
    public PussyDto findById(long id) throws Exception {
        var pussy = pussyMapping.ToDto(pussyDao.findById(id).orElse(new Pussy()));
        LOGGER.info(String.format("Message received GET ->%s", pussy));
        return pussy;
    }

    @KafkaListener(topics = "pussyTopic_json", groupId = "anotherGroup")
    public void addFriend(long firstPussyId, long secondPussyId) {
        var firstPussy = pussyDao.findById(firstPussyId).orElse(null);
        var secondPussy = pussyDao.findById(secondPussyId).orElse(null);
        firstPussy.getFriends().add(secondPussy);
        secondPussy.getFriends().add(firstPussy);
        pussyDao.save(firstPussy);
        pussyDao.save(secondPussy);
    }
}
