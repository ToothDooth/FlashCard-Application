package spring.ai.example.spring_ai_demo.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.ai.example.spring_ai_demo.Entity.Word;
import spring.ai.example.spring_ai_demo.Service.UrlRedirectorService;
import spring.ai.example.spring_ai_demo.Service.WordSpaService;

@Controller
@RequestMapping("/word")
public class WordController {

    @Autowired
    private UrlRedirectorService urlRedirectorService;

    @Autowired
    private WordSpaService wordSpaService;

    @GetMapping("/")
    public String wordSpa(Model model) {
        wordSpaService.loadWordSpa(model);
        return "Word/wordSpa";
    }

    @PostMapping("/save")
    public String saveWord(@ModelAttribute("word") Word word,
                           @RequestHeader(value = "Referer", required = false) String referer) {

        wordSpaService.saveWord(word);

        return urlRedirectorService.redirector(referer, 3);
    }

    @GetMapping("/close")
        public String closeWordResponse(@RequestHeader(value = "Referer", required = false) String referer, Model model) {
        wordSpaService.closeWordResponse(model);
        return urlRedirectorService.redirector(referer, 3);
        }
}
