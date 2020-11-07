package pl.edu.pk.schedulegenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.model.Teacher;
import pl.edu.pk.schedulegenerator.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @PostMapping("/addTeacher")
    public String saveTeacher(@RequestBody Teacher teacher) {
        teacherRepository.save(teacher);
        return "Added teacher: " + teacher.getName();
    }

    @GetMapping("/findAllTeachers")
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/findTeacherById/{id}")
    public Optional<Teacher> getTeacher(@PathVariable String id) {
        return teacherRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable String id) {
        teacherRepository.deleteById(id);
        return "Deleted teacher with id: " + id;
    }
}
