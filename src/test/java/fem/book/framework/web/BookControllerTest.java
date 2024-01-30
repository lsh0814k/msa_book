package fem.book.framework.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import fem.book.BookFactory;
import fem.book.BookInfoDTOFactory;
import fem.book.application.outputport.BookOutputPort;
import fem.book.domain.model.Book;
import fem.book.domain.model.vo.Classfication;
import fem.book.domain.model.vo.Location;
import fem.book.domain.model.vo.Source;
import fem.book.framework.jpaadapter.BookRepository;
import fem.book.framework.web.dto.BookInfoDTO;
import org.junit.jupiter.api.BeforeEach;
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
    @Autowired private BookFactory bookFactory;
    @Autowired private BookRepository bookRepository;

    @BeforeEach
    void init() {
        bookRepository.deleteAll();
    }

    @Test
    @DisplayName("도서 입고")
    void enterBook() throws Exception {
        BookInfoDTO bookInfoDTO = BookInfoDTOFactory.createBookInfoDTO();
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
        Book saved = bookOutputPort.save(bookFactory.createBook());

        mockMvc.perform(get("/api/books/" + saved.getNo())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookNo").value(saved.getNo()));
    }
}