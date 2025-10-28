package spring.ai.example.spring_ai_demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import spring.ai.example.spring_ai_demo.Entity.Word;

import java.util.List;

@RepositoryRestResource(path="word")
public interface WordRepository
        extends JpaRepository<Word, Integer> {

    Word findByName(String name);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update Word w set w.score = :score, w.tsQuizzed = CURRENT_TIMESTAMP, w.quizCount = :quizCount where w.id = :id")
    int updateQuizResultsById(@Param("id") Integer id, @Param("score") Float score, @Param("quizCount") Integer quizCount);

    @Query(value = "select * from word where word.name != :wordName order by rand() limit 3", nativeQuery = true)
    List<Word> findThreeRandomWords(@Param("wordName") String wordName);

    @Query(value = "select * from word order by rand() limit 1", nativeQuery = true)
    Word findOneRandomWord();

}
