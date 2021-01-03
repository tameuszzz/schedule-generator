package pl.edu.pk.schedulegenerator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.Entity.subject.Subject;
import pl.edu.pk.schedulegenerator.Entity.subject.SubjectUpdate;
import pl.edu.pk.schedulegenerator.Service.SubjectService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService service;


    @GetMapping
    public Collection<Subject> getSubjects() {
        return service.getSubjects();
    }

    @PostMapping
    public String postSubject(@Valid @RequestBody Subject subject, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = "Wystąpił błąd:  ";
            for (ObjectError objectError : errors.getAllErrors()) {
                errorMessage = errorMessage.concat(Objects.requireNonNull(objectError.getDefaultMessage()) + ' ');
            }
            return errorMessage;
        }
        service.postSubject(subject);
        return "Pomyślnie utworzono kierunek: " + subject.getName();
    }

    @GetMapping("/{id}")
    public Optional<Subject> getSubjectById(@PathVariable String id) {
        return service.getSubjectById(id);
    }

    @DeleteMapping("/{id}")
    public Optional<Subject> deleteSubjectById(@PathVariable String id) {
        return service.deleteSubjectById(id);
    }

    @PutMapping("/{id}")
    public String updateSubjectById(@PathVariable String id, @Valid @RequestBody SubjectUpdate subjectUpdate, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = "Wystąpił błąd:  ";
            for (ObjectError objectError : errors.getAllErrors()) {
                errorMessage = errorMessage.concat(Objects.requireNonNull(objectError.getDefaultMessage()) + ' ');
            }
            return errorMessage;
        }
        service.updateSubjectById(id, subjectUpdate);
        return "Pomyślnie edytowano kierunek: " + subjectUpdate.getName();

    }
}