package fem.book.framework.jpaadapter;

import fem.book.application.outputport.BookOutputPort;
import fem.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookAdapter implements BookOutputPort {

    private final BookRepository bookRepository;
    @Override
    public Book findByNo(long bookNo) {
        return bookRepository.findById(bookNo)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 도서입니다."));
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
