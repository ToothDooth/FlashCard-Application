package spring.ai.example.spring_ai_demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Word")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(onlyExplicitlyIncluded = true)
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include()
    private Integer id;

    @Column(name = "name", length = 255, nullable = false, unique = true)
    @ToString.Include()
    private String name;

    @Column(name = "meaning", columnDefinition = "TEXT")
    @ToString.Include()
    private String meaning;

    @JsonIgnore
    @Column(name = "score", nullable = false, columnDefinition = "FLOAT DEFAULT 0")
    private Float score = Float.valueOf(0);

    @JsonIgnore
    @Column(name = "quizCount", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer quizCount = Integer.valueOf(0);;

//    One-to-many relationship with sentence table.
    @OneToMany(mappedBy = "fetchWord", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Sentence> sentenceList;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "TsCreated", nullable = false, updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp tsCreated;

    @JsonIgnore
    @Column(name = "TsQuizzed", nullable = true, updatable = true)
    private Timestamp tsQuizzed;



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Word that = (Word) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) &&  Objects.equals(meaning, that.meaning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, meaning);
    }
}
