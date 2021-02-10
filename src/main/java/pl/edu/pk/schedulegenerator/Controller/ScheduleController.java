package pl.edu.pk.schedulegenerator.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.Entity.schedule.AvailabilityData;
import pl.edu.pk.schedulegenerator.Entity.schedule.Schedule;
import pl.edu.pk.schedulegenerator.Entity.schedule.ScheduleUpdate;
import pl.edu.pk.schedulegenerator.Service.ScheduleService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = "*")
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
    public String postSchedule(@Valid @RequestBody AvailabilityData availabilityData, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = "Wystąpił błąd:  ";
            for (ObjectError objectError : errors.getAllErrors()) {
                errorMessage = errorMessage.concat(Objects.requireNonNull(objectError.getDefaultMessage()) + ' ');
            }
            return errorMessage;
        }
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

    @PutMapping("/{id}")
    public String updateScheduleById(@PathVariable String id, @Valid @RequestBody ScheduleUpdate scheduleUpdate, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = "Wystąpił błąd:  ";
            for (ObjectError objectError : errors.getAllErrors()) {
                errorMessage = errorMessage.concat(Objects.requireNonNull(objectError.getDefaultMessage()) + ' ');
            }
            return errorMessage;
        }
        service.updateScheduleById(id, scheduleUpdate);
        return "Pomyślnie edytowano rozkład zajęć: " + scheduleUpdate.getName();
    }

}