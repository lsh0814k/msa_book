package fem.book.domain.model;

import fem.book.domain.model.vo.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Builder(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode(of = "no")
public class Book {
    @Id @GeneratedValue
    private Long no;
    private String title;
    @Embedded
    private BookDesc desc;
    @Enumerated(value = STRING)
    private Classfication classfication;
    @Enumerated(value = STRING)
    private BookStatus bookStatus;
    @Enumerated(value = STRING)
    private Location location;

    public static Book enterBook(String title, String author, String isbn, String description, LocalDate publication,
                                 Source source, Classfication classfication, Location location) {
        return Book.builder()
                .desc(BookDesc.createBookDesc(description, author, isbn, publication, source))
                .title(title)
                .classfication(classfication)
                .bookStatus(BookStatus.ENTERED)
                .location(location)
                .build();
    }

    public Book makeAvailable() {
        this.bookStatus = BookStatus.AVAILABLE;
        return this;
    }

    public Book makeUnavailable() {
        this.bookStatus = BookStatus.UNAVAILABLE;
        return this;
    }
}
