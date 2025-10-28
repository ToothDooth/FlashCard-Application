package spring.ai.example.spring_ai_demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import spring.ai.example.spring_ai_demo.Entity.Sentence;

@RepositoryRestResource(path="sentence")
public interface SentenceRepository
        extends JpaRepository<Sentence, Integer> {
}
