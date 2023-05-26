package micro.pussy.kafka;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import micro.pussy.dto.OwnerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class OwnerChangesProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OwnerChangesProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    public OwnerChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage() {
        String topic = "ownerTopic_json";
        EventHandler eventHandler = new OwnerChangesEventHandler(kafkaTemplate, topic);
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create("http://localhost:8090/swagger-ui/index.html#/owner-controller"));
        EventSource eventSource = builder.build();
        eventSource.start();
    }

    public void sendMessage(OwnerDto ownerDto) {
        String topic = "ownerTopic_json";
        EventHandler eventHandler = new OwnerChangesEventHandler(kafkaTemplate, topic);
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create("http://localhost:8080/owner-service"));
        EventSource eventSource = builder.build();
        eventSource.start();
    }

    public void sendMessage(List<OwnerDto> ownerDtoList) {
        String topic = "ownerTopic_json";
        EventHandler eventHandler = new OwnerChangesEventHandler(kafkaTemplate, topic);
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create("http://localhost:8080/owner-service"));
        EventSource eventSource = builder.build();
        eventSource.start();
    }
}
