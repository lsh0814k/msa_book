package fem.book.framework.kafkaadapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fem.book.domain.model.event.EventResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RentBookEventProducer {
    private static final String TOPIC = "TOPIC_RENT_RESULT";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void occurEvent(EventResult eventResult) {
        try {
            this.kafkaTemplate.send(TOPIC, objectMapper.writeValueAsString(eventResult));
        } catch (JsonProcessingException e) {
            log.error("json 변환 에러 : ", e);
            throw new IllegalStateException(e);
        }
    }

}
