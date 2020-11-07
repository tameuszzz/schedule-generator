package pl.edu.pk.schedulegenerator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pk.schedulegenerator.model.DepartmentApi;

@Repository
public interface DepartmentApiRepository extends MongoRepository<DepartmentApi, String> {
}
