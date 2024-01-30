package fem.book.application.inputport;

import fem.book.application.outputport.BookOutputPort;
import fem.book.application.usecase.InquiryUsecase;
import fem.book.domain.model.Book;
import fem.book.framework.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InquiryInputPort implements InquiryUsecase {
    private final BookOutputPort bookOutputPort;
    @Override
    public BookOutputDTO findBook(Long bookNo) {
        Book book = bookOutputPort.findByNo(bookNo);
        return BookOutputDTO.mapToDTO(book);
    }
}
