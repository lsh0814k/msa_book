package fem.book.application.inputport;

import fem.book.BookFactory;
import fem.book.application.outputport.BookOutputPort;
import fem.book.domain.model.Book;
import fem.book.domain.model.vo.BookStatus;
import fem.book.domain.model.vo.Classfication;
import fem.book.domain.model.vo.Location;
import fem.book.domain.model.vo.Source;
import fem.book.framework.web.dto.BookOutputDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MakeUnAvailableInputPortTest {

    @Autowired private MakeUnAvailableInputPort makeUnAvailableInputPort;
    @Autowired private BookOutputPort bookOutputPort;
    @Autowired private BookFactory bookFactory;

    @Test
    @DisplayName("대여 불가능 상태로 변경")
    void unavailable() {
        Book book = bookFactory.createBook();
        bookOutputPort.save(book);

        BookOutputDTO bookOutputDTO = makeUnAvailableInputPort.unavailable(book.getNo());

        assertThat(bookOutputDTO.getBookStatus()).isEqualTo(BookStatus.UNAVAILABLE.toString());
    }
}