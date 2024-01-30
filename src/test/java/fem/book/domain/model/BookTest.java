package fem.book.domain.model;

import fem.book.domain.model.vo.BookStatus;
import fem.book.domain.model.vo.Classfication;
import fem.book.domain.model.vo.Location;
import fem.book.domain.model.vo.Source;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class BookTest {

    @Test
    @DisplayName("입고")
    void enter() {
        Book book = enterBook();
        assertThat(book.getBookStatus()).isEqualTo(BookStatus.ENTERED);
    }

    @Test
    @DisplayName("대여 가능 상태로 변경")
    void changeAvailable() {
        Book book = enterBook();
        book.makeAvailable();

        assertThat(book.getBookStatus()).isEqualTo(BookStatus.AVAILABLE);
    }

    @Test
    @DisplayName("대여 불가능 상태로 변경")
    void changeUnavailable() {
        Book book = enterBook();
        book.makeUnavailable();

        assertThat(book.getBookStatus()).isEqualTo(BookStatus.UNAVAILABLE);
    }

    private Book enterBook() {
        return Book.enterBook(
                "노인과 바다",
                "헤밍웨이",
                "202401301513",
                "어니스트 헤밍웨이가 1952년에 발표한 중편소설",
                LocalDate.of(1954, 1, 1),
                Source.DONATION,
                Classfication.LITERATURE,
                Location.PANGYO
        );
    }
}
