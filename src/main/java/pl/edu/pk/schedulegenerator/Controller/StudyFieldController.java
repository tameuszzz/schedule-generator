package pl.edu.pk.schedulegenerator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/get")
    public Collection<StudyField> getStudyFields() {
        return service.getStudyFields();
    }

    @PostMapping("/post")
    public StudyField postStudyField(@Valid @RequestBody StudyField studyField) {
        return service.postStudyField(studyField);
    }

    @GetMapping("/get/{id}")
    public Optional<StudyField> getStudyFieldById(@PathVariable String id) {
        return service.getStudyFieldsById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Optional<StudyField> deleteStudyFieldById(@PathVariable String id) {
        return service.deleteStudyFieldById(id);
    }

    @PutMapping("/put/{id}")
    public Optional<StudyField> updateStudyFieldById(@PathVariable String id, @RequestBody StudyFieldUpdate studyFieldUpdate) {
        return service.updateStudyFieldById(id, studyFieldUpdate);
    }

}
