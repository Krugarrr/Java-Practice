package micro.pussy.kafka;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import micro.pussy.dto.OwnerDto;
import micro.pussy.dto.PussyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class PussyChangesProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(PussyChangesProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    public PussyChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage() {
        String topic = "pussyTopic_json";
        EventHandler eventHandler = new PussyChangesEventHandler(kafkaTemplate, topic);
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create("http://localhost:8090/swagger-ui/index.html#/owner-controller"));
        EventSource eventSource = builder.build();
        eventSource.start();
    }

    public void sendMessage(PussyDto pussyDto) {
        String topic = "pussyTopic_json";
        EventHandler eventHandler = new PussyChangesEventHandler(kafkaTemplate, topic);
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create("http://localhost:8080/owner-service"));
        EventSource eventSource = builder.build();
        eventSource.start();
    }

    public void sendMessage(List<PussyDto> pussyDtoList) {
        String topic = "pussyTopic_json";
        EventHandler eventHandler = new PussyChangesEventHandler(kafkaTemplate, topic);
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create("http://localhost:8080/owner-service"));
        EventSource eventSource = builder.build();
        eventSource.start();
    }
}
