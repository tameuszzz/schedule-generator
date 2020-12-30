package pl.edu.pk.schedulegenerator.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.Entity.schedule.AvailabilityData;
import pl.edu.pk.schedulegenerator.Entity.schedule.Schedule;
import pl.edu.pk.schedulegenerator.Service.ScheduleService;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService service;


    @GetMapping
    public Collection<Schedule> getSchedules() {
        return service.getSchedules();
    }

    @PostMapping
    public String postSchedule(@RequestBody AvailabilityData availabilityData) {
        log.info("ScheduleController - postSchedule");
        //log.info(String.valueOf(availabilityData));
        return service.postSchedule(availabilityData);
    }

    @GetMapping("/{id}")
    public Optional<Schedule> getScheduleById(@PathVariable String id) {
        return service.getScheduleById(id);
    }

    @DeleteMapping("/{id}")
    public Optional<Schedule> deleteScheduleById(@PathVariable String id) {
        return service.deleteScheduleById(id);
    }

}