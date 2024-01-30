package fem.book.application.usecase;

import fem.book.framework.web.dto.BookOutputDTO;

public interface InquiryUsecase {
    BookOutputDTO findBook(Long bookNo);
}
