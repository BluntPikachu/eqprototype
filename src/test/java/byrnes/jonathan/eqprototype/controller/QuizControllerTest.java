package byrnes.jonathan.eqprototype.controller;

import byrnes.jonathan.eqprototype.dto.CreateQuestionDto;
import byrnes.jonathan.eqprototype.dto.CreateQuizDto;
import byrnes.jonathan.eqprototype.exceptions.GlobalExceptionHandler;
import byrnes.jonathan.eqprototype.model.*;
import byrnes.jonathan.eqprototype.service.QuizService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class QuizControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QuizService quizService;

    @InjectMocks
    private QuizController quizController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(quizController)
                .setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    void testCreate_Success() throws Exception {
        CreateQuizDto createQuizDto = new CreateQuizDto(
                "Test Quiz", "Test quiz description", false);

        Quiz quiz = createFakeQuiz(createQuizDto);

        when(quizService.create(eq("user123"), eq("category123"), any(CreateQuizDto.class)))
                .thenReturn(quiz);

        mockMvc.perform(post("/api/quiz/create")
                        .param("userId", "user123")
                        .param("categoryId", "category123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createQuizDto)))
                .andExpect(status().isOk());
    }

    private Quiz createFakeQuiz(CreateQuizDto createQuizDto) {
        Category category = new Category("None");
        LinkedRole linkedRole = new LinkedRole(new Role("USER"));
        User user = new User(
                linkedRole, "email", "password", new Date(), false
        );
        user.setId("user123");
        category.setId("category123");

        return new Quiz(
                user, category, createQuizDto.getTitle(), createQuizDto.getDescription(),
                createQuizDto.isActive(), new Date());
    }


}