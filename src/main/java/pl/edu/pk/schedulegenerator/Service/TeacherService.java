package pl.edu.pk.schedulegenerator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pk.schedulegenerator.DAO.TeacherDAO;
import pl.edu.pk.schedulegenerator.Entity.Teacher;
import pl.edu.pk.schedulegenerator.Entity.TeacherUpdate;

import java.util.Collection;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherDAO dao;

    public Collection<Teacher> getTeachers() {
        return dao.getTeachers();
    }

    public Teacher postTeacher(Teacher teacher) {
        return dao.postTeacher(teacher);
    }

    public Optional<Teacher> getTeacherById(String id) {
        return dao.getTeacherById(id);
    }

    public Optional<Teacher> deleteTeacherById(String id) {
        return dao.deleteTeacherById(id);
    }

    public Optional<Teacher> updateTeacherById(String id, TeacherUpdate teacherUpdate) {
        return dao.updateTeacherById(id, teacherUpdate);
    }
}
