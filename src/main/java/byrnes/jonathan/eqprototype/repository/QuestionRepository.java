package byrnes.jonathan.eqprototype.repository;

import byrnes.jonathan.eqprototype.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    List<Question> findByQuizId(String quizId);

}
