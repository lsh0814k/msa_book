package fem.book.framework.kafkaadapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fem.book.application.usecase.MakeAvailableUsecase;
import fem.book.domain.model.event.ItemReturned;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReturnBookEventConsumer {
    private final ObjectMapper objectMapper;
    private final MakeAvailableUsecase makeAvailableUsecase;
    private static final String TOPIC = "TOPIC_RETURN";

    @KafkaListener(topics = TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void consumeReturn(ConsumerRecord<String, String> record) {
        try {
            ItemReturned itemReturned = objectMapper.readValue(record.value(), ItemReturned.class);
            makeAvailableUsecase.available(itemReturned.getItem().getNo());
        } catch (JsonProcessingException e) {
            log.error("json 변환 에러 : ", e);
            throw new IllegalStateException(e);
        }

    }

}
