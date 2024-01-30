package fem.book.framework.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import fem.book.application.outputport.BookOutputPort;
import fem.book.domain.model.Book;
import fem.book.domain.model.vo.Classfication;
import fem.book.domain.model.vo.Location;
import fem.book.domain.model.vo.Source;
import fem.book.framework.web.dto.BookInfoDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private BookOutputPort bookOutputPort;

    @Test
    @DisplayName("도서 입고")
    void enterBook() throws Exception {
        BookInfoDTO bookInfoDTO = createBookInfoDTO();
        mockMvc.perform(post("/api/books")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookInfoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookNo").isNotEmpty())
                .andExpect(jsonPath("$.bookTitle").value("노인과 바다"));

    }

    @Test
    @DisplayName("도서 조회")
    void findBook() throws Exception {
        Book saved = bookOutputPort.save(createBook());

        mockMvc.perform(get("/api/books/" + saved.getNo())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookNo").value(saved.getNo()));
    }

    private BookInfoDTO createBookInfoDTO() {
        return BookInfoDTO.createBookInfoDTO(
                "노인과 바다",
                "헤밍웨이",
                "202401301513",
                "어니스트 헤밍웨이가 1952년에 발표한 중편소설",
                LocalDate.of(1954, 1, 1),
                Source.DONATION.toString(),
                Classfication.LITERATURE.toString(),
                Location.PANGYO.toString()
        );
    }

    private Book createBook() {
        return Book.enterBook(
                "노인과 바다",
                "헤밍웨이",
                "202401301513",
                "어니스트 헤밍웨이가 1952년에 발표한 중편소설",
                LocalDate.of(1954, 1, 1),
                Source.DONATION,
                Classfication.LITERATURE,
                Location.PANGYO);
    }
}