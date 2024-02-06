package fem.book.framework.kafkaadapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fem.book.application.usecase.MakeUnAvailableUsecase;
import fem.book.domain.model.event.ItemRented;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RentBookEventConsumer {
    private final ObjectMapper objectMapper;
    private final MakeUnAvailableUsecase makeUnAvailableUsecase;
    private static final String TOPIC = "TOPIC_RENT";

    @KafkaListener(topics = TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void consumeRental(ConsumerRecord<String, String> record) {
        try {
            ItemRented itemRented = objectMapper.readValue(record.value(), ItemRented.class);
            makeUnAvailableUsecase.unavailable(itemRented.getItem().getNo());
        } catch (JsonProcessingException e) {
            log.error("json 변환 에러 : ", e);
            throw new IllegalStateException(e);
        }
    }
}
