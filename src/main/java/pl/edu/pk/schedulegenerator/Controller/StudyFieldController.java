package pl.edu.pk.schedulegenerator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.Entity.StudyField;
import pl.edu.pk.schedulegenerator.Entity.StudyFieldUpdate;
import pl.edu.pk.schedulegenerator.Service.StudyFieldService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/fields")
public class StudyFieldController {

    @Autowired
    private StudyFieldService service;

    @GetMapping
    public Collection<StudyField> getStudyFields() {
        return service.getStudyFields();
    }

    @PostMapping
    public String postStudyField(@Valid @RequestBody StudyField studyField, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = "Wystąpił błąd:  ";
            for (ObjectError objectError : errors.getAllErrors()) {
                errorMessage = errorMessage.concat(Objects.requireNonNull(objectError.getDefaultMessage()) + ' ');
            }
            return errorMessage;
        }
        service.postStudyField(studyField);
        return "Pomyślnie utworzono nowy kierunek: " + studyField.getName();
    }

    @GetMapping("/{id}")
    public Optional<StudyField> getStudyFieldById(@PathVariable String id) {
        return service.getStudyFieldsById(id);
    }

    @DeleteMapping("/{id}")
    public Optional<StudyField> deleteStudyFieldById(@PathVariable String id) {
        return service.deleteStudyFieldById(id);
    }

    @PutMapping("/{id}")
    public String updateStudyFieldById(@PathVariable String id, @Valid @RequestBody StudyFieldUpdate studyFieldUpdate, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = "Wystąpił błąd:  ";
            for (ObjectError objectError : errors.getAllErrors()) {
                errorMessage = errorMessage.concat(Objects.requireNonNull(objectError.getDefaultMessage()) + ' ');
            }
            return errorMessage;
        }
        service.updateStudyFieldById(id, studyFieldUpdate);
        return "Pomyślnie edytowano kierunek: " + studyFieldUpdate.getName();

    }

}
