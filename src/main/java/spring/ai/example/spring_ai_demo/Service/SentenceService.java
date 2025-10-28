package spring.ai.example.spring_ai_demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ai.example.spring_ai_demo.DAO.SentenceRepository;
import spring.ai.example.spring_ai_demo.Entity.Sentence;
import spring.ai.example.spring_ai_demo.Entity.Word;

import java.util.List;

@Service
public class SentenceService {
    @Autowired
    private final SentenceRepository sentenceRepository;
    public SentenceService(SentenceRepository sentenceRepository) {
        this.sentenceRepository = sentenceRepository;
    }


    // Nullify primary keys to avoid conflicts when saving new entities to the database
    public Word nullifyPrimaryKeys(Word word) {
        for (Sentence sentence: word.getSentenceList()) {
            sentence.setId(null);
            sentence.setFetchWord(word);
        }
        return word;
    }
}
