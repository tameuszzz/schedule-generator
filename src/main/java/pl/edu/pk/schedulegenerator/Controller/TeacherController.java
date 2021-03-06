package pl.edu.pk.schedulegenerator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.Entity.Teacher;
import pl.edu.pk.schedulegenerator.Entity.TeacherUpdate;
import pl.edu.pk.schedulegenerator.Service.TeacherService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @GetMapping
    public Collection<Teacher> getTeachers() {
        return service.getTeachers();
    }

    @PostMapping
    public String postTeacher(@Valid @RequestBody Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = "Wystąpił błąd:  ";
            for (ObjectError objectError : errors.getAllErrors()) {
                errorMessage = errorMessage.concat(Objects.requireNonNull(objectError.getDefaultMessage()) + ' ');
            }
            return errorMessage;
        }
        service.postTeacher(teacher);
        return "Pomyślnie utworzono nowego nauczyciela akademickiego: " + teacher.getName();
    }

    @GetMapping("/{id}")
    public Optional<Teacher> getTeacherById(@PathVariable String id) {
        return service.getTeacherById(id);
    }

    @DeleteMapping("/{id}")
    public Optional<Teacher> deleteTeacherById(@PathVariable String id) {
        return service.deleteTeacherById(id);
    }

    @PutMapping("/{id}")
    public String updateTeacherById(@PathVariable String id, @Valid @RequestBody TeacherUpdate teacherUpdate, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = "Wystąpił błąd:  ";
            for (ObjectError objectError : errors.getAllErrors()) {
                errorMessage = errorMessage.concat(Objects.requireNonNull(objectError.getDefaultMessage()) + ' ');
            }
            return errorMessage;
        }
        service.updateTeacherById(id, teacherUpdate);
        return "Pomyślnie edytowano nauczyciela akademickiego: " + teacherUpdate.getName();
    }
}
