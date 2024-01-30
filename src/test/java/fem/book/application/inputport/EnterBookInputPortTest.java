package fem.book.application.inputport;

import fem.book.application.outputport.BookOutputPort;
import fem.book.domain.model.vo.BookStatus;
import fem.book.domain.model.vo.Classfication;
import fem.book.domain.model.vo.Location;
import fem.book.domain.model.vo.Source;
import fem.book.framework.web.dto.BookInfoDTO;
import fem.book.framework.web.dto.BookOutputDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class EnterBookInputPortTest {
    @Autowired EnterBookInputPort enterBookInputPort;
    @Autowired BookOutputPort bookOutputPort;

    @Test
    @DisplayName("도서 입고")
    void enterBook() {
        BookInfoDTO bookInfoDTO = createBookInfoDTO();
        BookOutputDTO bookOutputDTO = enterBookInputPort.enterBook(bookInfoDTO);

        assertThat(bookOutputDTO.getBookNo()).isNotNull();
        assertThat(bookOutputDTO.getBookStatus()).isEqualTo(BookStatus.ENTERED.toString());
    }

    private BookInfoDTO createBookInfoDTO() {
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