package spring.ai.example.spring_ai_demo.Service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class WordAlreadyExistsException extends RuntimeException {

    public WordAlreadyExistsException(String wordName) {
        super("This word already exists in the DB: " + wordName);
    }
}
