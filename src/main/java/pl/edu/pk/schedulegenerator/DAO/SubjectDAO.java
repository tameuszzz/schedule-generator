package pl.edu.pk.schedulegenerator.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.schedulegenerator.Entity.subject.Subject;
import pl.edu.pk.schedulegenerator.Entity.subject.SubjectUpdate;

import java.util.Collection;
import java.util.Optional;

@Component
public class SubjectDAO {

    @Autowired
    private SubjectRepository repository;

    public Collection<Subject> getSubjects() {
        return repository.findAll();
    }

    public Subject postSubject(Subject subject) {
        return repository.insert(subject);
    }

    public Optional<Subject> getSubjectById(String id) {
        return repository.findById(id);
    }

    public Optional<Subject> deleteSubjectById(String id) {
        Optional<Subject> subject = repository.findById(id);
        subject.ifPresent(s -> repository.delete(s));
        return subject;
    }

    public Optional<Subject> updateSubjectById(String id, SubjectUpdate subjectUpdate) {
        Optional<Subject> subject = repository.findById(id);
        subject.ifPresent(s-> s.setName(subjectUpdate.getName()));
        subject.ifPresent(s-> s.setEligibility(subjectUpdate.isEligibility()));
        subject.ifPresent(s-> s.setSchedule(subjectUpdate.getSchedule()));
        subject.ifPresent(s-> s.setStudents(subjectUpdate.getStudents()));
        subject.ifPresent(s-> s.setTeachers(subjectUpdate.getTeachers()));
        subject.ifPresent(s -> repository.save(s));
        return subject;
    }
}
