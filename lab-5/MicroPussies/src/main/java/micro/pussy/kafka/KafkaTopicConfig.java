package micro.pussy.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic microPussiesTopic(){
        return TopicBuilder.name("pussyTopic").build();
    }
    @Bean
    public NewTopic microPussiesJsonTopic(){
        return TopicBuilder.name("pussyTopic_json").build();
    }
}
