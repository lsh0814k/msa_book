package fem.book.framework.jpaadapter;

import fem.book.BookFactory;
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
    @Autowired private BookFactory bookFactory;

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
        Book book = bookFactory.createBook();
        Book savedBook = bookOutputPort.save(book);
        Book findBook = bookAdapter.findByNo(savedBook.getNo());

        assertThat(findBook.getNo()).isEqualTo(savedBook.getNo());
    }

    @Test
    @DisplayName("도서 입고")
    void save() {
        Book book = bookFactory.createBook();
        Book saved = bookAdapter.save(book);
        Book findBook = bookOutputPort.findByNo(saved.getNo());

        assertThat(findBook).isNotNull();
    }
}