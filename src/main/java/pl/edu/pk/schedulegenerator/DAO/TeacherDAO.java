package pl.edu.pk.schedulegenerator.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import pl.edu.pk.schedulegenerator.Entity.StudyField;
import pl.edu.pk.schedulegenerator.Entity.subject.Subject;
import pl.edu.pk.schedulegenerator.Entity.teacher.HoursByType;
import pl.edu.pk.schedulegenerator.Entity.teacher.NewSubjectTeacher;
import pl.edu.pk.schedulegenerator.Entity.teacher.Teacher;
import pl.edu.pk.schedulegenerator.Entity.teacher.TeacherUpdate;
import pl.edu.pk.schedulegenerator.Service.SubjectService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class TeacherDAO {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private SubjectRepository subRepository;

    @Autowired
    private StudyFieldRepository studyFieldRepository;

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

        if(teacherUpdate.getHoursByType() == null) {
            HoursByType hoursByType = new HoursByType();
            teacherUpdate.setHoursByType(hoursByType);
        }
        Optional<Teacher> teacher = repository.findById(id);
        teacher.ifPresent(t -> t.setName(teacherUpdate.getName()));
        teacher.ifPresent(t -> t.setTitleID(teacherUpdate.getTitleID()));
        teacher.ifPresent(t -> t.setStudyFieldId(teacherUpdate.getStudyFieldId()));
        teacher.ifPresent(t -> t.setHours(teacherUpdate.getHours()));
        teacher.ifPresent(t -> t.setHoursByType(teacherUpdate.getHoursByType()));
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

    public NewSubjectTeacher addHoursToTeacher(NewSubjectTeacher newSubjectTeacher) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(newSubjectTeacher.getSubjectName()));
        List<Subject> subjects = mongoTemplate.find(query, Subject.class);

        Optional<StudyField> studyField = studyFieldRepository.findById(subjects.get(0).getStudents().getStudyFieldID());
        List<StudyField> studyFields = studyFieldRepository.findAll();

        Optional<Teacher> teacher = repository.findById(newSubjectTeacher.getTeacherId());

        HoursByType hoursByType = teacher.get().getHoursByType();

        float hours = 0;
        float rate = subjects.get(0).getRate();

        if(newSubjectTeacher.isLecturesEnable()) {
            hours += rate * (newSubjectTeacher.getLecturesHours() *
                    Integer.parseInt(subjects.get(0).getSchedule().getLectures().getHours()));

        }

        if(newSubjectTeacher.isExerciseEnable()) {
            hours += rate * (newSubjectTeacher.getExerciseHours() *
                    Integer.parseInt(subjects.get(0).getSchedule().getExercise().getHours()));
        }

        if(newSubjectTeacher.isLaboratoriesEnable()) {
            hours += rate * (newSubjectTeacher.getLaboratoriesHours() *
                    Integer.parseInt(subjects.get(0).getSchedule().getLaboratories().getHours()));
        }

        if (newSubjectTeacher.isSeminarsEnable()) {
            hours += rate * (newSubjectTeacher.getSeminarsHours() *
                    Integer.parseInt(subjects.get(0).getSchedule().getSeminars().getHours()));
        }

        int hoursRounded = Math.round(hours);
        int sem = subjects.get(0).getStudents().getSemester();
        boolean isWinter = sem % 2 != 0;

        hoursByType.setHoursCurr(hoursByType.getHoursCurr() + hoursRounded);

        if (studyField.get().getErasmus() == 2) {
            // stopien studiow
            if (studyField.get().getDegree() == 1) {
                // stacjonarne
                if (studyField.get().getFullTime() == 1) {
                    if (isWinter) {
                        hoursByType.setHoursIstZ(hoursByType.getHoursIstZ() + hoursRounded);
                    } else {
                        hoursByType.setHoursIstL(hoursByType.getHoursIstL() + hoursRounded);
                    }
                } else {
                    if (isWinter) {
                        hoursByType.setHoursInstZ(hoursByType.getHoursInstZ() + hoursRounded);
                    } else {
                        hoursByType.setHoursInstL(hoursByType.getHoursInstL() + hoursRounded);
                    }
                }
            } else {
                // stacjonarne
                if (studyField.get().getFullTime() == 1) {
                    if (isWinter) {
                        hoursByType.setHoursIIstZ(hoursByType.getHoursIIstZ() + hoursRounded);
                    } else {
                        hoursByType.setHoursIIstL(hoursByType.getHoursIIstL() + hoursRounded);
                    }
                } else {
                    if (isWinter) {
                        hoursByType.setHoursIInstZ(hoursByType.getHoursIInstZ() + hoursRounded);
                    } else {
                        hoursByType.setHoursIInstL(hoursByType.getHoursIInstL() + hoursRounded);
                    }
                }

            }
        } else {
            hoursByType.setHoursErasmus(hoursByType.getHoursErasmus() + hoursRounded);
        }
        teacher.ifPresent(t -> t.setHoursByType(hoursByType));
        teacher.ifPresent(t -> repository.save(t));

        return null;
    }

    public NewSubjectTeacher removeHoursFromTeacher(NewSubjectTeacher newSubjectTeacher) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(newSubjectTeacher.getSubjectName()));
        List<Subject> subjects = mongoTemplate.find(query, Subject.class);

        Optional<StudyField> studyField = studyFieldRepository.findById(subjects.get(0).getStudents().getStudyFieldID());
        List<StudyField> studyFields = studyFieldRepository.findAll();

        Optional<Teacher> teacher = repository.findById(newSubjectTeacher.getTeacherId());

        HoursByType hoursByType = teacher.get().getHoursByType();

        float hours = 0;
        float rate = subjects.get(0).getRate();

        if(newSubjectTeacher.isLecturesEnable()) {
            hours += rate * (newSubjectTeacher.getLecturesHours() *
                    Integer.parseInt(subjects.get(0).getSchedule().getLectures().getHours()));
        }

        if(newSubjectTeacher.isExerciseEnable()) {
            hours += rate * (newSubjectTeacher.getExerciseHours() *
                    Integer.parseInt(subjects.get(0).getSchedule().getExercise().getHours()));
        }

        if(newSubjectTeacher.isLaboratoriesEnable()) {
            hours += rate * (newSubjectTeacher.getLaboratoriesHours() *
                    Integer.parseInt(subjects.get(0).getSchedule().getLaboratories().getHours()));
        }

        if (newSubjectTeacher.isSeminarsEnable()) {
            hours += rate * (newSubjectTeacher.getSeminarsHours() *
                    Integer.parseInt(subjects.get(0).getSchedule().getSeminars().getHours()));
        }

        int hoursRounded = Math.round(hours);
        int sem = subjects.get(0).getStudents().getSemester();
        boolean isWinter = sem % 2 != 0;

        hoursByType.setHoursCurr(hoursByType.getHoursCurr() - hoursRounded);

        if (studyField.get().getErasmus() == 2) {
            // stopien studiow
            if (studyField.get().getDegree() == 1) {
                // stacjonarne
                if (studyField.get().getFullTime() == 1) {
                    if (isWinter) {
                        hoursByType.setHoursIstZ(hoursByType.getHoursIstZ() - hoursRounded);
                    } else {
                        hoursByType.setHoursIstL(hoursByType.getHoursIstL() - hoursRounded);
                    }
                } else {
                    if (isWinter) {
                        hoursByType.setHoursInstZ(hoursByType.getHoursInstZ() - hoursRounded);
                    } else {
                        hoursByType.setHoursInstL(hoursByType.getHoursInstL() - hoursRounded);
                    }
                }
            } else {
                // stacjonarne
                if (studyField.get().getFullTime() == 1) {
                    if (isWinter) {
                        hoursByType.setHoursIIstZ(hoursByType.getHoursIIstZ() - hoursRounded);
                    } else {
                        hoursByType.setHoursIIstL(hoursByType.getHoursIIstL() - hoursRounded);
                    }
                } else {
                    if (isWinter) {
                        hoursByType.setHoursIInstZ(hoursByType.getHoursIInstZ() - hoursRounded);
                    } else {
                        hoursByType.setHoursIInstL(hoursByType.getHoursIInstL() - hoursRounded);
                    }
                }

            }
        } else {
            hoursByType.setHoursErasmus(hoursByType.getHoursErasmus() - hoursRounded);
        }

        teacher.ifPresent(t -> t.setHoursByType(hoursByType));
        teacher.ifPresent(t -> repository.save(t));

        return null;
    }
}
