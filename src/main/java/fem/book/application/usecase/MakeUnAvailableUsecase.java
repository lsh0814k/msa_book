package fem.book.application.usecase;


import fem.book.framework.web.dto.BookOutputDTO;

public interface MakeUnAvailableUsecase {
    BookOutputDTO unavailable(Long bookNo);

}
