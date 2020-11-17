package pl.edu.pk.schedulegenerator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.Entity.Teacher;
import pl.edu.pk.schedulegenerator.Entity.TeacherUpdate;
import pl.edu.pk.schedulegenerator.Service.TeacherService;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @GetMapping("/get")
    public Collection<Teacher> getTeachers() {
        return service.getTeachers();
    }

    @PostMapping("/post")
    public Teacher postTeacher(@RequestBody Teacher teacher) {
        return service.postTeacher(teacher);
    }

    @GetMapping("/get/{id}")
    public Optional<Teacher> getTeacherById(@PathVariable String id) {
        return service.getTeacherById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Optional<Teacher> deleteTeacherById(@PathVariable String id) {
        return service.deleteTeacherById(id);
    }

    @PutMapping("/put/{id}")
    public Optional<Teacher> updateTeacherById(@PathVariable String id, @RequestBody TeacherUpdate teacherUpdate) {
        return service.updateTeacherById(id, teacherUpdate);
    }
}
