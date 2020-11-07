package pl.edu.pk.schedulegenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.model.DepartmentApi;
import pl.edu.pk.schedulegenerator.repository.DepartmentApiRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/department")
public class DepartmentApiController {

    @Autowired
    private DepartmentApiRepository departmentRepository;

    @PostMapping("/addDepartment")
    public String saveDepartment(@RequestBody DepartmentApi department) {
        departmentRepository.save(department);
        return "Added department: " + department.getName();
    }

    @GetMapping("/findAllDepartments")
    public List<DepartmentApi> getDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/findDepartmentById/{id}")
    public Optional<DepartmentApi> getDepartment(@PathVariable String id) {
        return departmentRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable String id) {
        departmentRepository.deleteById(id);
        return "Deleted department with id: " + id;
    }
}
