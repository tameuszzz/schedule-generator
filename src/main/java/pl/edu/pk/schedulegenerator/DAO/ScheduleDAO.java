package pl.edu.pk.schedulegenerator.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.schedulegenerator.Entity.schedule.Schedule;

import java.util.Collection;
import java.util.Optional;

@Component
public class ScheduleDAO {

    @Autowired
    private ScheduleRepository repository;

    public Collection<Schedule> getSchedules() {
        return repository.findAll();
    }

    public Schedule postSchedule(Schedule schedule) {
        return repository.insert(schedule);
    }

    public Optional<Schedule> getScheduleById(String id) {
        return repository.findById(id);
    }

    public Optional<Schedule> deleteScheduleById(String id) {
        Optional<Schedule> schedule = repository.findById(id);
        schedule.ifPresent(c -> repository.delete(c));
        return schedule;
    }
}
