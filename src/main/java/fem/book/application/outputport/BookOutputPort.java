package fem.book.application.outputport;

import fem.book.domain.model.Book;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookOutputPort {
    Book findByNo(long bookNo);

    Book save(Book book);
}
