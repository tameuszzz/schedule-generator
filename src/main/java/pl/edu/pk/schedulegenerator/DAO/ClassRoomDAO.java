package pl.edu.pk.schedulegenerator.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.schedulegenerator.Entity.ClassRoom;
import pl.edu.pk.schedulegenerator.Entity.ClassRoomUpdate;

import java.util.Collection;
import java.util.Optional;

@Component
public class ClassRoomDAO {

    @Autowired
    private ClassRoomRepository repository;

    public Collection<ClassRoom> getClassRooms() {
        return repository.findAll();
    }

    public ClassRoom createClassRoom(ClassRoom classRoom) {
        return repository.insert(classRoom);
    }

    public Optional<ClassRoom> getClassRoomsById(String id) {
        return repository.findById(id);
    }

    public Optional<ClassRoom> deleteClassRoomsById(String id) {
        Optional<ClassRoom> classRoom = repository.findById(id);
        classRoom.ifPresent(c -> repository.delete(c));
        return classRoom;
    }

    public Optional<ClassRoom> updateClassRoomsById(String id, ClassRoomUpdate classRoomUpdate) {
        Optional<ClassRoom> classRoom = repository.findById(id);
        classRoom.ifPresent(c -> c.setName(classRoomUpdate.getName()));
        classRoom.ifPresent(c -> c.setAvailability(classRoomUpdate.getAvailability()));
        classRoom.ifPresent(c -> repository.save(c));
        return classRoom;
    }
}
