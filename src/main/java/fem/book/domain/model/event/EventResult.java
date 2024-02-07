package fem.book.domain.model.event;

import lombok.*;

import java.io.Serializable;

import static lombok.AccessLevel.*;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@Builder(access = PRIVATE)
public class EventResult implements Serializable {
    private boolean isSuccess;
    private IDName idName;
    private Item item;
    private long point;

    public static EventResult create(boolean isSuccess, IDName idName, Item item, long point) {
        return EventResult
                .builder()
                .isSuccess(isSuccess)
                .idName(idName)
                .item(item)
                .point(point)
                .build();
    }
}
