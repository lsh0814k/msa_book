package fem.book.application.inputport;


import fem.book.application.outputport.BookOutputPort;
import fem.book.application.usecase.EnterBookUsecase;
import fem.book.domain.model.Book;
import fem.book.domain.model.vo.Classfication;
import fem.book.domain.model.vo.Location;
import fem.book.domain.model.vo.Source;
import fem.book.framework.web.dto.BookInfoDTO;
import fem.book.framework.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EnterBookInputPort implements EnterBookUsecase {
    private final BookOutputPort bookOutputPort;

    @Override
    public BookOutputDTO enterBook(BookInfoDTO bookInfoDTO) {
        Book book = Book.enterBook(
                bookInfoDTO.getTitle(),
                bookInfoDTO.getAuthor(),
                bookInfoDTO.getIsbn(),
                bookInfoDTO.getDescription(),
                bookInfoDTO.getPublicationDate(),
                Source.valueOf(bookInfoDTO.getSource()),
                Classfication.valueOf(bookInfoDTO.getClassification()),
                Location.valueOf(bookInfoDTO.getLocation()));
        Book savedBook = bookOutputPort.save(book);

        return BookOutputDTO.mapToDTO(savedBook);
    }
}
