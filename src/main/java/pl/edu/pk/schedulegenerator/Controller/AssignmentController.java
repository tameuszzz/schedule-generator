package pl.edu.pk.schedulegenerator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.Entity.assignment.Assignment;
import pl.edu.pk.schedulegenerator.Entity.assignment.AssignmentUpdate;
import pl.edu.pk.schedulegenerator.Service.AssignmentService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService service;

    @GetMapping
    public Collection<Assignment> getAssignments() {
        return service.getAssignments();
    }

    @PostMapping
    public String postAssignment(@Valid @RequestBody Assignment assignment, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = "Wystąpił błąd:  ";
            for (ObjectError objectError : errors.getAllErrors()) {
                errorMessage = errorMessage.concat(Objects.requireNonNull(objectError.getDefaultMessage()) + ' ');
            }
            return errorMessage;
        }
        System.out.println(assignment);
        service.createAssignment(assignment);
        return "Pomyślnie utworzono przydział: " + assignment.getName();
    }

    @GetMapping("/{id}")
    public Optional<Assignment> getAssignmentById(@PathVariable String id) {
        return service.getAssignmentById(id);
    }

    @DeleteMapping("/{id}")
    public Optional<Assignment> deleteAssignmentById(@PathVariable String id) {
        return service.deleteAssignmentById(id);
    }

    @PutMapping("/{id}")
    public String updateAssignmentById(@PathVariable String id, @Valid @RequestBody AssignmentUpdate assignmentUpdate, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = "Wystąpił błąd:  ";
            for (ObjectError objectError : errors.getAllErrors()) {
                errorMessage = errorMessage.concat(Objects.requireNonNull(objectError.getDefaultMessage()) + ' ');
            }
            return errorMessage;
        }
        service.updateAssignmentById(id, assignmentUpdate);
        return "Pomyślnie edytowano przydział: " + assignmentUpdate.getName();
    }

}
