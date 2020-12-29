package pl.edu.pk.schedulegenerator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.Entity.ClassRoom;
import pl.edu.pk.schedulegenerator.Entity.ClassRoomUpdate;
import pl.edu.pk.schedulegenerator.Service.ClassRoomService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<String> postClassRoom(@RequestBody ClassRoom classRoom) {
        service.createClassRoom(classRoom);
        return ResponseEntity.ok("Pomyślnie utworzono salę: " + classRoom.getName());
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
    public ResponseEntity<String> updateClassRoomById(@PathVariable String id, @Valid @RequestBody ClassRoomUpdate classRoomUpdate) {
        service.updateClassRoomById(id, classRoomUpdate);
        return ResponseEntity.ok("Pomyślnie edytowano salę: " + classRoomUpdate.getName());
    }
}
