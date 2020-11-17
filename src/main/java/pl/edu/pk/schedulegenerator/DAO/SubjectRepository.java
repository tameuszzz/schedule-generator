package pl.edu.pk.schedulegenerator.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pk.schedulegenerator.Entity.subject.Subject;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, String> {

}
