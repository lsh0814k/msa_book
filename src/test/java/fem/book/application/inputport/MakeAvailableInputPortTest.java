package fem.book.application.inputport;

import fem.book.application.outputport.BookOutputPort;
import fem.book.domain.model.Book;
import fem.book.domain.model.vo.BookStatus;
import fem.book.domain.model.vo.Classfication;
import fem.book.domain.model.vo.Location;
import fem.book.domain.model.vo.Source;
import fem.book.framework.web.dto.BookOutputDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MakeAvailableInputPortTest {

    @Autowired private MakeAvailableInputPort makeAvailableInputPort;
    @Autowired private BookOutputPort bookOutputPort;

    @Test
    @DisplayName("대여 가능 상태로 변경")
    void available() {
        Book book = createBook();
        bookOutputPort.save(book);

        BookOutputDTO bookOutputDTO = makeAvailableInputPort.available(book.getNo());

        assertThat(bookOutputDTO.getBookStatus()).isEqualTo(BookStatus.AVAILABLE.toString());
    }

    private Book createBook() {
        return Book.enterBook(
                "노인과 바다",
                "헤밍웨이",
                "202401301513",
                "어니스트 헤밍웨이가 1952년에 발표한 중편소설",
                LocalDate.of(1954, 1, 1),
                Source.DONATION,
                Classfication.LITERATURE,
                Location.PANGYO);
    }
}