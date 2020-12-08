package pl.edu.pk.schedulegenerator.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pk.schedulegenerator.Entity.schedule.Schedule;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String>  {
}
