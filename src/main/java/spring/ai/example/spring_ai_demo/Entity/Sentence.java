package spring.ai.example.spring_ai_demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Sentence")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(onlyExplicitlyIncluded = true)
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "TEXT")
    @ToString.Include()
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fetchWordid", nullable = false)
    private Word fetchWord;
}
