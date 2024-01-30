package fem.book.framework.web;

import fem.book.application.usecase.EnterBookUsecase;
import fem.book.application.usecase.InquiryUsecase;
import fem.book.framework.web.dto.BookInfoDTO;
import fem.book.framework.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    private final EnterBookUsecase enterBookUsecase;
    private final InquiryUsecase inquiryUsecase;

    @PostMapping("/books")
    public ResponseEntity enterBook(@RequestBody BookInfoDTO bookInfoDTO) {
        BookOutputDTO bookOutputDTO = enterBookUsecase.enterBook(bookInfoDTO);
        return ResponseEntity.status(CREATED)
                .body(bookOutputDTO);
    }

    @GetMapping("/books/{bookNo}")
    public ResponseEntity book(@PathVariable("bookNo") Long bookNo) {
        BookOutputDTO bookOutputDTO = inquiryUsecase.findBook(bookNo);
        return ResponseEntity.status(OK)
                .body(bookOutputDTO);
    }
}
