package fem.book;

import fem.book.domain.model.vo.Classfication;
import fem.book.domain.model.vo.Location;
import fem.book.domain.model.vo.Source;
import fem.book.framework.web.dto.BookInfoDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class BookInfoDTOFactory {
    public static BookInfoDTO createBookInfoDTO() {
        return BookInfoDTO.createBookInfoDTO(
                "노인과 바다",
                "헤밍웨이",
                "202401301513",
                "어니스트 헤밍웨이가 1952년에 발표한 중편소설",
                LocalDate.of(1954, 1, 1),
                Source.DONATION.toString(),
                Classfication.LITERATURE.toString(),
                Location.PANGYO.toString()
        );
    }
}
