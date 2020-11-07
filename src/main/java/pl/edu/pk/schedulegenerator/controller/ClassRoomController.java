package pl.edu.pk.schedulegenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.model.ClassRoom;
import pl.edu.pk.schedulegenerator.repository.ClassRoomRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/classroom")
public class ClassRoomController {

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @PostMapping("/addClassRoom")
    public String saveClassRoom(@RequestBody ClassRoom classRoom) {
        classRoomRepository.save(classRoom);
        return "Added class room: " + classRoom.getName();
    }

    @GetMapping("/findAllClassRooms")
    public List<ClassRoom> getClassRooms() {
        return classRoomRepository.findAll();
    }

    @GetMapping("/findClassRoomId/{id}")
    public Optional<ClassRoom> getClassRoom(@PathVariable String id) {
        return classRoomRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteClassRoom(@PathVariable String id) {
        classRoomRepository.deleteById(id);
        return "Deleted class room with id: " + id;
    }
}
