package fem.book.application.inputport;

import fem.book.application.outputport.BookOutputPort;
import fem.book.application.usecase.MakeAvailableUsecase;
import fem.book.domain.model.Book;
import fem.book.framework.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MakeAvailableInputPort implements MakeAvailableUsecase {
    private final BookOutputPort bookOutputPort;
    @Override
    public BookOutputDTO available(Long bookNo) {
        Book book = bookOutputPort.findByNo(bookNo);
        book.makeAvailable();
        return BookOutputDTO.mapToDTO(book);
    }
}
