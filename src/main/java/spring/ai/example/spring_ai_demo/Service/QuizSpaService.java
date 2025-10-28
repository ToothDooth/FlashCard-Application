package spring.ai.example.spring_ai_demo.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import spring.ai.example.spring_ai_demo.Entity.Word;

import java.util.List;

//Service for preparing the model for the Quiz controller.
@Service
@Slf4j
@Data
public class QuizSpaService {

    private QuizService quizService;

    @Autowired
    public QuizSpaService(
        QuizService quizService
    ) {
        this.quizService = quizService;
    }

    public void loadQuizSpa(Model model) {
        addWordNameToModel(model);
        addOptionsListToModel(model);
    }

    public void loadQuizCheckAndRespondSpa(Integer responseOptionId, Model model) {
        boolean answerIsCorrect = this.quizService.checkAnswer(responseOptionId);
        this.quizService.updateScore(answerIsCorrect);

        if (answerIsCorrect) {
            addWordToModel(model);
            addShowResponseToModel(model);
            this.quizService.reset();
        } else {
            this.quizService.removeFromOptionsLIst(responseOptionId);
            addWordNameToModel(model);
            addOptionsListToModel(model);
        }
    }

    private void addWordNameToModel(Model model) {
        Word currentWord = this.quizService.getQuizWord();
        model.addAttribute("quizWordName", currentWord.getName());
    }

    private void addWordToModel(Model model) {
        Word currentWord = this.quizService.getQuizWord();
        model.addAttribute("word", currentWord);
    }

    private void addOptionsListToModel(Model model) {
        List<Word> optionsList = this.quizService.getOptionsList();
        model.addAttribute("optionsList", optionsList);
    }

    // shows or hides the response section in the SPA
    private void addShowResponseToModel(Model model) {
        model.addAttribute("showResponse", true);
    }
}
