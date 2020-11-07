package pl.edu.pk.schedulegenerator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pk.schedulegenerator.model.ClassRoom;

@Repository
public interface ClassRoomRepository extends MongoRepository<ClassRoom, String> {
}
