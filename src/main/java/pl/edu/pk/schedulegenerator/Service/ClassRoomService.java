package pl.edu.pk.schedulegenerator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pk.schedulegenerator.DAO.ClassRoomDAO;
import pl.edu.pk.schedulegenerator.Entity.ClassRoom;
import pl.edu.pk.schedulegenerator.Entity.ClassRoomUpdate;

import java.util.Collection;
import java.util.Optional;

@Service
public class ClassRoomService {

    @Autowired
    private ClassRoomDAO dao;

    public Collection<ClassRoom> getClassRooms() {
        return dao.getClassRooms();
    }

    public ClassRoom createClassRoom(ClassRoom classRoom) {
        return dao.createClassRoom(classRoom);
    }

    public Optional<ClassRoom> getClassRoomById(String id) {
        return dao.getClassRoomsById(id);
    }

    public Optional<ClassRoom> deleteClassRoomById(String id) {
        return dao.deleteClassRoomsById(id);
    }

    public Optional<ClassRoom> updateClassRoomById(String id, ClassRoomUpdate classRoomUpdate) {
        return dao.updateClassRoomsById(id, classRoomUpdate);
    }
}
