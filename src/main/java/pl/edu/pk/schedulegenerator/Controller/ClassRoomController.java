package pl.edu.pk.schedulegenerator.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.Entity.ClassRoom;
import pl.edu.pk.schedulegenerator.Entity.ClassRoomUpdate;
import pl.edu.pk.schedulegenerator.Service.ClassRoomService;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/classroom")
public class ClassRoomController {

    @Autowired
    private ClassRoomService service;

    Logger logger = LoggerFactory.getLogger(ClassRoomController.class);

    @GetMapping("/get")
    public Collection<ClassRoom> getClassRooms() {
        logger.info("An INFO Message");
        return service.getClassRooms();
    }

    @PostMapping("/post")
    public ClassRoom postClassRoom(@RequestBody ClassRoom classRoom) {
        return service.createClassRoom(classRoom);
    }

    @GetMapping("/get/{id}")
    public Optional<ClassRoom> getClassRoomById(@PathVariable String id) {
        return service.getClassRoomById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Optional<ClassRoom> deleteClassRoomById(@PathVariable String id) {
        return service.deleteClassRoomById(id);
    }

    @PutMapping("/put/{id}")
    public Optional<ClassRoom> updateClassRoomById(@PathVariable String id, @RequestBody ClassRoomUpdate classRoomUpdate) {
        return service.updateClassRoomById(id, classRoomUpdate);
    }
}
