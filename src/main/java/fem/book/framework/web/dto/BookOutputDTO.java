package fem.book.framework.web.dto;

import fem.book.domain.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class BookOutputDTO {
    private Long bookNo;
    private String bookTitle;
    private String bookStatus;

    public static BookOutputDTO mapToDTO(Book book) {
        return BookOutputDTO.builder()
                .bookNo(book.getNo())
                .bookTitle(book.getTitle())
                .bookStatus(book.getBookStatus().toString())
                .build();
    }
}
