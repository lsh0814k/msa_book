package fem.book.domain.model;

import fem.book.BookFactory;
import fem.book.domain.model.vo.BookStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookTest {
    private BookFactory bookFactory = new BookFactory();

    @Test
    @DisplayName("입고")
    void enter() {
        Book book = bookFactory.createBook();
        assertThat(book.getBookStatus()).isEqualTo(BookStatus.ENTERED);
    }

    @Test
    @DisplayName("대여 가능 상태로 변경")
    void changeAvailable() {
        Book book = bookFactory.createBook();
        book.makeAvailable();

        assertThat(book.getBookStatus()).isEqualTo(BookStatus.AVAILABLE);
    }

    @Test
    @DisplayName("대여 불가능 상태로 변경")
    void changeUnavailable() {
        Book book = bookFactory.createBook();
        book.makeUnavailable();

        assertThat(book.getBookStatus()).isEqualTo(BookStatus.UNAVAILABLE);
    }
}
