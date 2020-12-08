package pl.edu.pk.schedulegenerator.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import pl.edu.pk.schedulegenerator.Entity.StudyField;
import pl.edu.pk.schedulegenerator.Entity.StudyFieldUpdate;
import pl.edu.pk.schedulegenerator.Entity.Teacher;
import pl.edu.pk.schedulegenerator.Service.TeacherService;

import java.util.Collection;
import java.util.Optional;

@Component
public class StudyFieldDAO {

    @Autowired
    private StudyFieldRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Collection<StudyField> getStudyFields() {
        return repository.findAll();
    }

    public StudyField postStudyField(StudyField studyField) {
        return repository.insert(studyField);
    }

    public Optional<StudyField> getStudyFieldsById(String id) {
        return repository.findById(id);
    }

    public Optional<StudyField> deleteStudyFieldById(String id) {
        Optional<StudyField> studyField = repository.findById(id);
        studyField.ifPresent(s -> deleteTeacherByStudyFieldId(s.getId()));
        studyField.ifPresent(s -> repository.delete(s));
        return studyField;
    }

    private void deleteTeacherByStudyFieldId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("studyFieldId").is(id));
        Collection<Teacher> teachers = mongoTemplate.find(query, Teacher.class);
        TeacherService ts = new TeacherService();
        for(Teacher teacher : teachers) {
            ts.deleteTeacherById(teacher.getId());
        }
    }

    public Optional<StudyField> updateStudyFieldById(String id, StudyFieldUpdate studyFieldUpdate) {
        Optional<StudyField> studyField = repository.findById(id);
        studyField.ifPresent(s -> s.setName(studyFieldUpdate.getName()));
        studyField.ifPresent(s -> s.setDegree(studyFieldUpdate.getDegree()));
        studyField.ifPresent(s -> s.setNumberOfSemesters(studyFieldUpdate.getNumberOfSemesters()));
        studyField.ifPresent(s -> repository.save(s));
        return studyField;
    }
}
