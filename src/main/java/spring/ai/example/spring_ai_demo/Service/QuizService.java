package spring.ai.example.spring_ai_demo.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.ai.example.spring_ai_demo.DAO.WordRepository;
import spring.ai.example.spring_ai_demo.Entity.Word;

import java.util.Collections;
import java.util.List;

//Quiz service for interactive with the word Dao
@Service
@Slf4j
@Data
public class QuizService {

    private WordRepository wordRepository;
    private List<Word> optionsList;
    private Word quizWord;
    private float score;

    @Autowired
    public QuizService(
            WordRepository wordRepository,
            WordService wordService
    ) {
        this.wordRepository = wordRepository;

        this.initializeQuiz();
    }

    public void initializeQuiz() {
        this.quizWord = wordRepository.findOneRandomWord();
        this.score = 1;
        this.optionsList = wordRepository.findThreeRandomWords(this.quizWord.getName());
        this.optionsList.add(this.quizWord);
        Collections.shuffle(optionsList);
    }

    public void reset() {
        this.initializeQuiz();
    }

    public boolean checkAnswer(Integer responseOptionId) {
        return this.quizWord.getId().equals(responseOptionId);
    }

    public void updateScore(boolean isCorrect) {
        if (!isCorrect) {
            score -= 0.25;
        }
        wordRepository.updateQuizResultsById(
            this.quizWord.getId(),
            this.quizWord.getScore() + score,
            this.quizWord.getQuizCount()+1
        );
    }

    public void removeFromOptionsLIst(Integer responseOptionId) {
        if (responseOptionId != null) {
            optionsList.removeIf(option -> option.getId() == responseOptionId);
        }
    }
}
