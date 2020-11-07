package pl.edu.pk.schedulegenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.model.StudyField;
import pl.edu.pk.schedulegenerator.repository.StudyFieldRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/fields")
public class StudyFieldController {

    private StudyFieldRepository studyFieldRepository;

    @Autowired
    public StudyFieldController(StudyFieldRepository studyFieldRepository) {
        this.studyFieldRepository = studyFieldRepository;
    }

    @PostMapping("/addStudyField")
    public String saveStudyField(@RequestBody StudyField studyField) {
        studyFieldRepository.save(studyField);
        return "Added study field: " + studyField.getName();
    }

    @GetMapping("/findAllStudyFields")
    public List<StudyField> getStudyFields() {
        return studyFieldRepository.findAll();
    }

    @GetMapping("/findStudyField/{id}")
    public Optional<StudyField> getStudyField(@PathVariable String id) {
        return studyFieldRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudyField(@PathVariable String id) {
        studyFieldRepository.deleteById(id);
        return "Deleted study field with id: " + id;
    }
}
