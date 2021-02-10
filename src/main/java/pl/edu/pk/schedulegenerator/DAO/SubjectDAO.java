package pl.edu.pk.schedulegenerator.DAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import pl.edu.pk.schedulegenerator.Entity.ClassRoom;
import pl.edu.pk.schedulegenerator.Entity.assignment.Assignment;
import pl.edu.pk.schedulegenerator.Entity.assignment.TeacherAssignments;
import pl.edu.pk.schedulegenerator.Entity.subject.Subject;
import pl.edu.pk.schedulegenerator.Entity.subject.SubjectTeachers;
import pl.edu.pk.schedulegenerator.Entity.subject.SubjectUpdate;
import pl.edu.pk.schedulegenerator.Entity.teacher.Teacher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class SubjectDAO {

    @Autowired
    private SubjectRepository repository;
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Collection<Subject> getSubjects() {
        Collection<Subject> subjects = repository.findAll();
        Collection<ClassRoom> classRooms = classRoomRepository.findAll();
        ArrayList<String> classes = new ArrayList<>();
        classRooms.forEach(classRoom -> classes.add(classRoom.getName().replaceAll("\\s","")));


        for(Subject subject : subjects) {
            if (subject.getSchedule().getLectures().isEnabled()
                    && !subject.getSchedule().getLectures().isOnline()) {
                for (String className : subject.getSchedule().getLectures().getClassroom()) {
                    if (!classes.contains(className.replaceAll("\\s",""))) {
                        subject.setError("Nie znaleziono klasy: " + className);
                        break;
                    }
                }
            }
            if (subject.getSchedule().getExercise().isEnabled()
                    && !subject.getSchedule().getExercise().isOnline()) {
                for (String className : subject.getSchedule().getExercise().getClassroom()) {
                    if (!classes.contains(className.replaceAll("\\s",""))) {
                        subject.setError("Nie znaleziono klasy: " + className);
                        break;
                    }
                }
            }
            if (subject.getSchedule().getLaboratories().isEnabled()
                    && !subject.getSchedule().getLaboratories().isOnline()) {
                for (String className : subject.getSchedule().getLaboratories().getClassroom()) {
                    if (!classes.contains(className.replaceAll("\\s",""))) {
                        subject.setError("Nie znaleziono klasy: " + className);
                        break;
                    }
                }
            }
            if (subject.getSchedule().getSeminars().isEnabled()
                    && !subject.getSchedule().getSeminars().isOnline()) {
                for (String className : subject.getSchedule().getSeminars().getClassroom()) {
                    if (!classes.contains(className.replaceAll("\\s",""))) {
                        subject.setError("Nie znaleziono klasy: " + className);
                        break;
                    }
                }
            }

        }

        return subjects;
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
        subject.ifPresent(s-> s.setRate(subjectUpdate.getRate()));
        subject.ifPresent(s-> s.setSchedule(subjectUpdate.getSchedule()));
        subject.ifPresent(s-> s.setStudents(subjectUpdate.getStudents()));
        subject.ifPresent(s-> s.setError(subjectUpdate.getError()));
        subject.ifPresent(s-> s.setTeachers(subjectUpdate.getTeachers()));
        subject.ifPresent(s -> repository.save(s));
        return subject;
    }

    public String postAssignments(String id) {

        Optional<Assignment> assignment = assignmentRepository.findById(id);

        List<Subject> subjects = repository.findAll();
        ArrayList<SubjectTeachers> subjectTeachers = new ArrayList<>();
        if (!subjects.isEmpty()) {
            for (Subject subject : subjects) {
                subject.setTeachers(subjectTeachers);
                repository.save(subject);
            }
        }

        String name = "";
        for(Subject subject : subjects) {
            subjectTeachers.clear();
            System.out.println("Przedmiot: " + subject.getName());
            name = subject.getName();
            for (TeacherAssignments tAssignment: assignment.get().getAssignments()) {
                if (tAssignment.getSubjectName().equals(name)) {
                    System.out.println("Znaleniony przypis: " + tAssignment.getSubjectName());

                    SubjectTeachers subjectTeacher = new SubjectTeachers();
                    Optional<Teacher> teacher = teacherRepository.findById(tAssignment.getTeacherId());
                    teacher.ifPresent(subjectTeacher::setTeacher);

                    if (tAssignment.getLecturesGroups() != 0) {
                        subjectTeacher.setLecturesEnable(true);
                        subjectTeacher.setLecturesHours(tAssignment.getLecturesGroups());
                    }
                    if (tAssignment.getExerciseGroups() != 0) {
                        subjectTeacher.setExerciseEnable(true);
                        subjectTeacher.setExerciseHours(tAssignment.getExerciseGroups());
                    }
                    if (tAssignment.getLaboratoriesGroups() != 0) {
                        subjectTeacher.setLaboratoriesEnable(true);
                        subjectTeacher.setLaboratoriesHours(tAssignment.getLaboratoriesGroups());
                    }
                    if (tAssignment.getSeminarsGroups() != 0) {
                        subjectTeacher.setSeminarsEnable(true);
                        subjectTeacher.setSeminarsHours(tAssignment.getSeminarsGroups());
                    }
                    System.out.println("Wpisany przydział: " + subjectTeacher);
                    subjectTeachers.add(subjectTeacher);
                }
            }
            System.out.println("Zapis do przedmiotu: " + subject.getTeachers());
            repository.save(subject);
        }
        return "Przypisano nauczycieli";
    }
}
