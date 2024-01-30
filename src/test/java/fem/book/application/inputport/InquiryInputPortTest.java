package fem.book.application.inputport;

import fem.book.application.outputport.BookOutputPort;
import fem.book.domain.model.Book;
import fem.book.domain.model.vo.Classfication;
import fem.book.domain.model.vo.Location;
import fem.book.domain.model.vo.Source;
import fem.book.framework.jpaadapter.BookRepository;
import fem.book.framework.web.dto.BookOutputDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class InquiryInputPortTest {
    @Autowired private BookOutputPort bookOutputPort;
    @Autowired private InquiryInputPort inquiryInputPort;

    @Test
    @DisplayName("도서 찾기_존재 하지 않는 도서")
    void findBook_non_exist() {
        assertThatThrownBy(() -> inquiryInputPort.findBook(1L))
                .isInstanceOf(InvalidDataAccessApiUsageException.class)
                .message()
                .isEqualTo("존재하지 않는 도서입니다.");
    }

    @Test
    @DisplayName("도서 찾기")
    void findBook_exist() {
        Book book = createBook();
        Book savedBook = bookOutputPort.save(book);

        BookOutputDTO bookOutputDTO = inquiryInputPort.findBook(savedBook.getNo());
        assertThat(bookOutputDTO.getBookNo()).isEqualTo(savedBook.getNo());
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