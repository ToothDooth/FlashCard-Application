package spring.ai.example.spring_ai_demo.Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import spring.ai.example.spring_ai_demo.DAO.WordRepository;
import spring.ai.example.spring_ai_demo.Entity.Word;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@SpringBootTest
class WordServiceTest {

    @Mock
    private WordRepository wordRepository;
    @Mock
    private WordAi wordAi;
    @Mock
    private SentenceService sentenceService;
    private String request;
    private Word word;

    @InjectMocks
    private WordService wordService;



    @BeforeEach
    void setUp() {
        request = "toest";
        word = new Word();
        word.setName("toest");
        word.setMeaning("test");
        word.setId(1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getWordResponse() {
        when(wordAi.getWordResponse(request)).thenReturn(word);
        when(sentenceService.nullifyPrimaryKeys(word)).thenReturn(word);
        when(wordRepository.save(word)).thenReturn(word);
        assertEquals(word, wordService.getWordResponse(request));

    }
}