package fem.book.application.usecase;


import fem.book.framework.web.dto.BookOutputDTO;

public interface MakeAvailableUsecase {
    BookOutputDTO available(Long bookNo);
}
