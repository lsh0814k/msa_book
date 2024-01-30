package fem.book.application.usecase;

import fem.book.framework.web.dto.BookInfoDTO;
import fem.book.framework.web.dto.BookOutputDTO;

public interface EnterBookUsecase {
    public BookOutputDTO enterBook(BookInfoDTO bookInfoDTO);
}
