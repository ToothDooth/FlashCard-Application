package spring.ai.example.spring_ai_demo.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import spring.ai.example.spring_ai_demo.Entity.Word;

//Service for preparing Model for the  word controller
@Service
@Slf4j
public class WordSpaService {

    @Autowired
    private WordService wordService;
    private Word responseWord;

    public void loadWordSpa(Model model) {
        addBlankNewWordToModel(model);
        if (responseWord != null) {
            addResponseWordToModel(model);
            addShowResponseToModel(model);
        }
    }

    public void closeWordResponse(Model model) {
        responseWord = null;
        loadWordSpa(model);
    }

    private static void addBlankNewWordToModel(Model model) {
        model.addAttribute("word", new Word());
    }

    private void addResponseWordToModel(Model model) {
        model.addAttribute("responseWord", responseWord);
    }

    private void addShowResponseToModel(Model model) {
        model.addAttribute("showResponse", true);
    }

    // Serves the word save request from the SPA.
    public void saveWord( Word word) {
        if (onlyLatinLetters(word.getName())) {
                responseWord = wordService.getWordResponse(word.getName());
        }else  {

            log.warn(word.getName() + " is not a valid dutch word.");

        }
    }

    boolean onlyLatinLetters(String s) {
        return s != null && s.matches("^[A-Za-z]+$");
    }
}
