package pl.edu.pk.schedulegenerator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.Entity.subject.Subject;
import pl.edu.pk.schedulegenerator.Entity.subject.SubjectUpdate;
import pl.edu.pk.schedulegenerator.Service.SubjectService;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService service;


    @GetMapping("/get")
    public Collection<Subject> getSubjects() {
        return service.getSubjects();
    }

    @PostMapping("/post")
    public Subject postSubject(@RequestBody Subject subject) {
        return service.postSubject(subject);
    }

    @GetMapping("/get/{id}")
    public Optional<Subject> getSubjectById(@PathVariable String id) {
        return service.getSubjectById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Optional<Subject> deleteSubjectById(@PathVariable String id) {
        return service.deleteSubjectById(id);
    }

    @PutMapping("/put/{id}")
    public Optional<Subject> updateSubjectById(@PathVariable String id, @RequestBody SubjectUpdate subjectUpdate) {
        return service.updateSubjectById(id, subjectUpdate);
    }
}