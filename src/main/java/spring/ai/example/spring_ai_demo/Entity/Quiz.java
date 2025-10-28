package spring.ai.example.spring_ai_demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Quiz {
    private int id;
    private Word question;
    private int score;
}
