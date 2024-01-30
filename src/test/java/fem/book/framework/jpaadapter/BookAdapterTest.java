package fem.book.framework.jpaadapter;

import fem.book.application.outputport.BookOutputPort;
import fem.book.domain.model.Book;
import fem.book.domain.model.vo.Classfication;
import fem.book.domain.model.vo.Location;
import fem.book.domain.model.vo.Source;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
class BookAdapterTest {
    @Autowired private BookAdapter bookAdapter;
    @Autowired private BookOutputPort bookOutputPort;

    @Test
    @DisplayName("도서 찾기_존재하지 않는 도서")
    void findByNo_non_exist() {
        assertThatThrownBy(() -> bookAdapter.findByNo(1L))
                .isInstanceOf(InvalidDataAccessApiUsageException.class)
                .message()
                .isEqualTo("존재하지 않는 도서입니다.");
    }

    @Test
    @DisplayName("도서 찾기")
    void findByNo() {
        Book book = createBook();
        Book savedBook = bookOutputPort.save(book);
        Book findBook = bookAdapter.findByNo(savedBook.getNo());

        assertThat(findBook.getNo()).isEqualTo(savedBook.getNo());
    }

    @Test
    @DisplayName("도서 입고")
    void save() {
        Book book = createBook();
        Book saved = bookAdapter.save(book);
        Book findBook = bookOutputPort.findByNo(saved.getNo());

        assertThat(findBook).isNotNull();
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