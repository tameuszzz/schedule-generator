package pl.edu.pk.schedulegenerator.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import pl.edu.pk.schedulegenerator.Entity.Teacher;
import pl.edu.pk.schedulegenerator.Entity.TeacherUpdate;
import pl.edu.pk.schedulegenerator.Entity.subject.Subject;
import pl.edu.pk.schedulegenerator.Service.SubjectService;

import java.util.Collection;
import java.util.Optional;

@Component
public class TeacherDAO {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private SubjectService subjs;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Collection<Teacher> getTeachers() {
        return repository.findAll();
    }

    public Teacher postTeacher(Teacher teacher) {
        return repository.insert(teacher);
    }

    public Optional<Teacher> getTeacherById(String id) {
        return repository.findById(id);
    }

    public Optional<Teacher> deleteTeacherById(String id) {
        Optional<Teacher> teacher = repository.findById(id);
        teacher.ifPresent(t -> deleteTeacherFromSubjects(t.getId()));
        teacher.ifPresent(t -> repository.delete(t));
        return teacher;
    }

    public Optional<Teacher> updateTeacherById(String id, TeacherUpdate teacherUpdate) {
        Optional<Teacher> teacher = repository.findById(id);
        teacher.ifPresent(t -> t.setName(teacherUpdate.getName()));
        teacher.ifPresent(t -> t.setTitleID(teacherUpdate.getTitleID()));
        teacher.ifPresent(t -> t.setStudyFieldId(teacherUpdate.getStudyFieldId()));
        teacher.ifPresent(t -> t.setHours(teacherUpdate.getHours()));
        teacher.ifPresent(t -> t.setAvailability(teacherUpdate.getAvailability()));
        teacher.ifPresent(t -> repository.save(t));
        return teacher;
    }

    private void deleteTeacherFromSubjects(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("teachers.teacher._id").is(id));
        Collection<Subject> subjects = mongoTemplate.find(query, Subject.class);
        for (Subject subject : subjects) {
            if(subject.getTeachers().removeIf(subjectTeachers -> subjectTeachers.getTeacher().getId().equals(id))) {
                subjs.updateSubjectTeachers(subject);
            }
        }

    }
}
