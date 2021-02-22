package pl.edu.pk.schedulegenerator.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.schedulegenerator.Entity.assignment.Assignment;
import pl.edu.pk.schedulegenerator.Entity.assignment.AssignmentUpdate;
import pl.edu.pk.schedulegenerator.Entity.assignment.TeacherAssignments;
import pl.edu.pk.schedulegenerator.Entity.teacher.HoursByType;
import pl.edu.pk.schedulegenerator.Entity.teacher.NewSubjectTeacher;
import pl.edu.pk.schedulegenerator.Entity.teacher.Teacher;
import pl.edu.pk.schedulegenerator.Service.TeacherService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class AssignmentDAO {

    @Autowired
    private AssignmentRepository repository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    public Collection<Assignment> getAssignments() {
        resetHours();
        return repository.findAll();
    }

    public Assignment createAssignment(Assignment assignment) {
        return repository.insert(assignment);
    }

    public Optional<Assignment> getAssignmentById(String id) {
        Optional<Assignment> assignment = repository.findById(id);
        resetHours();
        for (TeacherAssignments teacherAssignment : assignment.get().getAssignments()) {
            NewSubjectTeacher subjectTeacher = new NewSubjectTeacher();
            subjectTeacher.setTeacherId(teacherAssignment.getTeacherId());
            subjectTeacher.setSubjectName(teacherAssignment.getSubjectName());
            if(teacherAssignment.getLecturesGroups() != 0) {
                subjectTeacher.setLecturesEnable(true);
                subjectTeacher.setLecturesHours(teacherAssignment.getLecturesGroups());
            }
            if(teacherAssignment.getExerciseGroups() != 0) {
                subjectTeacher.setExerciseEnable(true);
                subjectTeacher.setExerciseHours(teacherAssignment.getExerciseGroups());
            }
            if(teacherAssignment.getLaboratoriesGroups() != 0) {
                subjectTeacher.setLaboratoriesEnable(true);
                subjectTeacher.setLaboratoriesHours(teacherAssignment.getLaboratoriesGroups());
            }
            if(teacherAssignment.getSeminarsGroups() != 0) {
                subjectTeacher.setSeminarsEnable(true);
                subjectTeacher.setSeminarsHours(teacherAssignment.getSeminarsGroups());
            }
            teacherService.addHoursToTeacher(subjectTeacher);
        }

        return assignment;
    }

    public Optional<Assignment> deleteAssignmentById(String id) {
        Optional<Assignment> assignment = repository.findById(id);
        assignment.ifPresent(c -> repository.delete(c));
        return assignment;
    }

    public Optional<Assignment> updateAssignmentById(String id, AssignmentUpdate assignmentUpdate) {
        Optional<Assignment> assignment = repository.findById(id);
        assignment.ifPresent(c -> c.setName(assignmentUpdate.getName()));
        assignment.ifPresent(c -> c.setIfWinter(assignmentUpdate.isIfWinter()));
        assignment.ifPresent(c -> c.setGroups(assignmentUpdate.getGroups()));
        assignment.ifPresent(c -> c.setAssignments(assignmentUpdate.getAssignments()));
        assignment.ifPresent(c -> repository.save(c));
        return assignment;
    }

    private void resetHours() {
        HoursByType hoursByType = new HoursByType();
        List<Teacher> teachers = teacherRepository.findAll();
        for (Teacher teacher: teachers) {
            teacher.setHoursByType(hoursByType);
            teacherRepository.save(teacher);
        }
    }
}
