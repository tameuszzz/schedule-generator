package pl.edu.pk.schedulegenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.model.subject.Subject;
import pl.edu.pk.schedulegenerator.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @PostMapping("/addSubject")
    public String saveSubject(@RequestBody Subject subject) {
        subjectRepository.save(subject);
        return "Added subject: " + subject.getName();
    }

    @GetMapping("/findAllSubjects")
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @GetMapping("/findSubjectById/{id}")
    public Optional<Subject> getSubject(@PathVariable String id) {
        return subjectRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSubject(@PathVariable String id) {
        subjectRepository.deleteById(id);
        return "Deleted subject with id: " + id;
    }


}
