package pl.edu.pk.schedulegenerator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pk.schedulegenerator.DAO.SubjectDAO;
import pl.edu.pk.schedulegenerator.Entity.subject.Subject;
import pl.edu.pk.schedulegenerator.Entity.subject.SubjectUpdate;

import java.util.Collection;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectDAO dao;

    public Collection<Subject> getSubjects() {
        return dao.getSubjects();
    }

    public Subject postSubject(Subject subject) {
        return dao.postSubject(subject);
    }

    public Optional<Subject> getSubjectById(String id) {
        return dao.getSubjectById(id);
    }

    public Optional<Subject> deleteSubjectById(String id) {
        return dao.deleteSubjectById(id);
    }

    public Optional<Subject> updateSubjectById(String id, SubjectUpdate subjectUpdate) {
        return dao.updateSubjectById(id, subjectUpdate);
    }

    public void updateSubjectTeachers(Subject subject) {
        SubjectUpdate subjectUpdate = new SubjectUpdate();
        subjectUpdate.setName(subject.getName());
        subjectUpdate.setEligibility(subject.isEligibility());
        subjectUpdate.setSchedule(subject.getSchedule());
        subjectUpdate.setStudents(subject.getStudents());
        subjectUpdate.setTeachers(subject.getTeachers());
        dao.updateSubjectById(subject.getId(), subjectUpdate);
    }

    public String postAssignments(String id) {
        return dao.postAssignments(id);
    }
}
