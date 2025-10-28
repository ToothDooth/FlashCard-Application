package spring.ai.example.spring_ai_demo.DAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import spring.ai.example.spring_ai_demo.Entity.Word;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WordRepositoryTest {

    @Autowired
    private WordRepository wordRepository;

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLCont = new MySQLContainer(DockerImageName.parse("mysql:8.3.0"));

    final String name = "TestName" ;
    final String meaning = "TestMeaning";
    private Word dBRetrievedWord;

    @BeforeEach
    void setUp() {
        Word word = new Word();
        word.setName(name);
        word.setMeaning(meaning);
        wordRepository.save(word);
        dBRetrievedWord = wordRepository.findByName(name);
    }

    @AfterEach
    void tearDown() {
        wordRepository.deleteAll();
    }

    @Test
    void testConnectionEstablished() {
        assertThat(mySQLCont.isCreated()).isTrue();
        assertThat(mySQLCont.isRunning()).isTrue();
    }

    @Test
    @DisplayName("Find a word by its name.")
    void testFindByName() {
        assertNotNull(dBRetrievedWord, "Word could not be found by name.");
        assertEquals("TestName", dBRetrievedWord.getName(), "Finds the wrong records.");
    }


    @Test
    @DisplayName("Finds Random 3 words for answer options.")
    void testUpdateQuizResultsById() {
        Float intialScore = dBRetrievedWord.getScore();
        Integer quizId = dBRetrievedWord.getId();;
        int numberOfRowsUpdated = wordRepository.updateQuizResultsById(quizId,3.25F, 5);
        Word updatedWord = wordRepository.findById(quizId).orElse(null);

        assertEquals(1, numberOfRowsUpdated, "More than one row updated.");
        assertNotNull(updatedWord);
        assertEquals(intialScore + 3.25F, updatedWord.getScore(),"Score is not updated correctly.");
        assertEquals(5,updatedWord.getQuizCount(),"Quiz count is not updated correctly.");
        assertEquals(dBRetrievedWord.getId(), updatedWord.getId(), "Incorrect word record updated.");
        assertThat(updatedWord.getId()).isEqualTo(quizId);
    }

    @Test
    @DisplayName("Test finds Random 3 words for answer options.")
    void testFindThreeRandomWords() {
        List<Word> words = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Word tempWord = new Word();
            tempWord.setName(name.concat(String.valueOf(i)));
            tempWord.setMeaning(meaning.concat(String.valueOf(i)));
            words.add(tempWord);
        }
        wordRepository.saveAll(words);
        List<Word> returnedRandomWords1 = wordRepository.findThreeRandomWords(name);
        List<Word> returnedRandomWords2 = wordRepository.findThreeRandomWords(name);

        assertNotNull(dBRetrievedWord, "Retrieved excluded word is null.");
        assertFalse(returnedRandomWords1.contains(dBRetrievedWord), "Excluded Quiz word is in the list.");
        assertEquals(3, returnedRandomWords1.size(), "Returned wrong number of random words." );
        assertFalse(returnedRandomWords1.equals(returnedRandomWords2), "Retrieved words are not random.");
    }
}