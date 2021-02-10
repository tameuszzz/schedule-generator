package pl.edu.pk.schedulegenerator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.Entity.ClassRoom;
import pl.edu.pk.schedulegenerator.Entity.ClassRoomUpdate;
import pl.edu.pk.schedulegenerator.Service.ClassRoomService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/classroom")
public class ClassRoomController {

    @Autowired
    private ClassRoomService service;

    @GetMapping
    public Collection<ClassRoom> getClassRooms() {
        return service.getClassRooms();
    }

    @PostMapping
    public String postClassRoom(@Valid @RequestBody ClassRoom classRoom, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = "Wystąpił błąd:  ";
            for (ObjectError objectError : errors.getAllErrors()) {
                errorMessage = errorMessage.concat(Objects.requireNonNull(objectError.getDefaultMessage()) + ' ');
            }
            return errorMessage;
        }
        service.createClassRoom(classRoom);
        return "Pomyślnie utworzono salę: " + classRoom.getName();
    }

    @GetMapping("/{id}")
    public Optional<ClassRoom> getClassRoomById(@PathVariable String id) {
        return service.getClassRoomById(id);
    }

    @DeleteMapping("/{id}")
    public Optional<ClassRoom> deleteClassRoomById(@PathVariable String id) {
        return service.deleteClassRoomById(id);
    }

    @PutMapping("/{id}")
    public String updateClassRoomById(@PathVariable String id, @Valid @RequestBody ClassRoomUpdate classRoomUpdate, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = "Wystąpił błąd:  ";
            for (ObjectError objectError : errors.getAllErrors()) {
                errorMessage = errorMessage.concat(Objects.requireNonNull(objectError.getDefaultMessage()) + ' ');
            }
            return errorMessage;
        }
        service.updateClassRoomById(id, classRoomUpdate);
        return "Pomyślnie edytowano salę: " + classRoomUpdate.getName();
    }
}
