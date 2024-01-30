package fem.book.domain.model.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Embeddable
@Builder(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class BookDesc {
    private String description;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    @Enumerated(value = STRING)
    private Source source;

    public static BookDesc createBookDesc(String description, String author, String isbn,
                                          LocalDate publicationDate, Source source) {
        return BookDesc.builder()
                .description(description)
                .author(author)
                .isbn(isbn)
                .publicationDate(publicationDate)
                .source(source)
                .build();
    }
}
