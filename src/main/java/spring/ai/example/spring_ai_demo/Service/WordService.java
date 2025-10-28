package spring.ai.example.spring_ai_demo.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ai.example.spring_ai_demo.DAO.WordRepository;
import spring.ai.example.spring_ai_demo.Entity.Word;

//Service for interacting with the word DAO
@Slf4j
@Service
public class WordService {
    @Autowired
    private WordRepository wordRepository;
    @Autowired
    private WordAi wordAi;
    @Autowired
    private SentenceService sentenceService;

    // Recieves the LLM API response and send it to WordRepository for insertion.
    public Word getWordResponse(String name) {
        Word word = wordAi.getWordResponse(name);
        word = nullifyPrimaryKeys(word);
        try {
            return wordRepository.save(word);
        } catch (DataIntegrityViolationException ex) {
            throw new WordAlreadyExistsException(word.getName());
        }
    }

    // Nullify primary keys to avoid conflicts when saving new entities to the database.
    private Word nullifyPrimaryKeys(Word word) {
        word.setId(null);
        return sentenceService.nullifyPrimaryKeys(word);
    }
}
