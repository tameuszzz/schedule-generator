package pl.edu.pk.schedulegenerator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.Entity.Teacher;
import pl.edu.pk.schedulegenerator.Entity.TeacherUpdate;
import pl.edu.pk.schedulegenerator.Service.TeacherService;

import javax.validation.Valid;
import java.util.Collection;
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
    public ResponseEntity<String> postTeacher(@Valid @RequestBody Teacher teacher) {
        service.postTeacher(teacher);
        return ResponseEntity.ok("Pomyślnie utworzono nowego nauczyciela akademickiego: " +teacher.getName());
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
    public ResponseEntity<String> updateTeacherById(@PathVariable String id, @Valid @RequestBody TeacherUpdate teacherUpdate) {
        service.updateTeacherById(id, teacherUpdate);
        return ResponseEntity.ok("Pomyślnie edytowano nauczyciela akademickiego: " + teacherUpdate.getName());
    }
}
