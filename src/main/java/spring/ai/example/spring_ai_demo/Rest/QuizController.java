package spring.ai.example.spring_ai_demo.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.ai.example.spring_ai_demo.Service.QuizSpaService;
import spring.ai.example.spring_ai_demo.Service.UrlRedirectorService;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizSpaService quizSpaService;

    @GetMapping("/")
    public String quizSpa(Model model) {
        quizSpaService.loadQuizSpa(model);
        return "Quiz/quizSpa";
    }

    @PostMapping("/response/{id}")
    public String checkAndRespond(@PathVariable int id,
                                  Model model) {
        quizSpaService.loadQuizCheckAndRespondSpa(id, model);

        return "Quiz/quizSpa";
    }
}