package pl.edu.pk.schedulegenerator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.Entity.StudyField;
import pl.edu.pk.schedulegenerator.Entity.StudyFieldUpdate;
import pl.edu.pk.schedulegenerator.Service.StudyFieldService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<String> postStudyField(@Valid @RequestBody StudyField studyField) {
        service.postStudyField(studyField);
        return ResponseEntity.ok("Pomyślnie utworzono nowy kierunek: " + studyField.getName());
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
    public ResponseEntity<String> updateStudyFieldById(@PathVariable String id, @Valid @RequestBody StudyFieldUpdate studyFieldUpdate) {
        service.updateStudyFieldById(id, studyFieldUpdate);
        return ResponseEntity.ok("Pomyślnie edytowano kierunek: " + studyFieldUpdate.getName());

    }

}
