package fem.book.framework.kafkaadapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fem.book.application.usecase.MakeUnAvailableUsecase;
import fem.book.domain.model.event.EventResult;
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
    private final RentBookEventProducer rentBookEventProducer;
    private static final String TOPIC = "TOPIC_RENTAL_RESULT";

    @KafkaListener(topics = TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void consumeRental(ConsumerRecord<String, String> record) {
        EventResult eventResult = null;
        ItemRented itemRented = null;
        try {
            itemRented = objectMapper.readValue(record.value(), ItemRented.class);
            makeUnAvailableUsecase.unavailable(itemRented.getItem().getNo());
            eventResult = createEventResult(itemRented, true);
        } catch (JsonProcessingException e) {
            log.error("json 변환 에러 : ", e);
            throw new IllegalStateException("json 변환 오류가 발생했습니다.");
        } catch (IllegalStateException e) {
            log.error("상태 변경 오류 ", e);
            eventResult = createEventResult(itemRented, false);
        } catch (Exception e) {
            log.error("예상치 못한 대여 오류 ", e);
            eventResult = createEventResult(itemRented, false);
        }

        rentBookEventProducer.occurEvent(eventResult);

    }

    private EventResult createEventResult(ItemRented itemRented, boolean isSuccess) {
        return EventResult.create(isSuccess, itemRented.getIdName(), itemRented.getItem(), itemRented.getPoint());
    }
}
