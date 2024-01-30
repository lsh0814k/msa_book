package fem.book.framework.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class BookInfoDTO {
    private String title;
    private String description;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private String source;
    private String classification;
    private String location;

    public static BookInfoDTO createBookInfoDTO(
            String title,
            String description,
            String author,
            String isbn,
            LocalDate publicationDate,
            String source,
            String classification,
            String location) {

        return BookInfoDTO
                .builder()
                .title(title)
                .description(description)
                .author(author)
                .isbn(isbn)
                .publicationDate(publicationDate)
                .source(source)
                .classification(classification)
                .location(location)
                .build();

    }
}
