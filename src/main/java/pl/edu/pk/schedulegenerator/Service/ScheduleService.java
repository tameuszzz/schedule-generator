package pl.edu.pk.schedulegenerator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pk.schedulegenerator.DAO.ScheduleDAO;
import pl.edu.pk.schedulegenerator.Entity.schedule.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleDAO dao;

    public Collection<Schedule> getSchedules() {
        return dao.getSchedules();
    }

    public Schedule postSchedule(AvailabilityData avaData) {
        Schedule schedule = new Schedule();
        schedule.setName(avaData.getName());
        schedule.setIfWinter(avaData.isIfWinter());
        schedule.setStudyFieldId(avaData.getStudyFieldId());
        schedule.setNumberOfSemester(avaData.getNumberOfSemester());

        int firstIndex, lastIndex, start, end, equalsCounter = 0;
        boolean subjectFound, classroomFound = false;
        List<ArrayList<String>> classSubList;
        List<ArrayList<TeachersData>> teachersDataSubList;

        for (int s = 0; s < schedule.getNumberOfSemester(); s++) {
            Semester semester = new Semester();
            for (int d = 0; d < 5; d++) {
                DayOfWeek dayOfWeek = new DayOfWeek();
                for (int i = 0; i < 56; i++) {
                    firstIndex = d * 56;
                    lastIndex = firstIndex + 56;
                    start = firstIndex + i;
                    end = start + i + 6;    // 6 bo 6*15 = 90 czyli blok na przedmiot
                    if(end > lastIndex) break;
                    classSubList = avaData.getClassroomsData().subList(start, end);
                    teachersDataSubList = avaData.getTeachersData().subList(start, end);
//                    for (String className : classSubList.get(0)) {
//                        for (int l = 1; l < classSubList.size(); l++) {
//                            for (int k = 0; k < classSubList.get(l).size(); k++) {
//                                if(className.equals(classSubList.get(l).get(k))) {
//                                    classroomFound = true;
//                                    break;
//                                }
//                                classroomFound = false;
//                            }
//                            if (!classroomFound) break;
//                        }
//                        if (classroomFound) {
//
//                        }
//                    }

                    ScheduleSubject scheduleSubject = new ScheduleSubject();


                    for (String className : classSubList.get(0)) {
                        for (TeachersData teacherData : teachersDataSubList.get(0)) {
                            if (className.equals(teacherData.getClassName())) {
                                for (int l = 1; l < teachersDataSubList.size(); l++) {
                                    for (int k = 0; k < teachersDataSubList.get(l).size(); k++) {
                                        if (teacherData.equals(teachersDataSubList.get(l).get(k))) {
                                            equalsCounter++;
                                            if (equalsCounter == 6) {
                                                scheduleSubject.setSubjectName(teacherData.getSubjectName());
                                                scheduleSubject.setSubjectType(teacherData.getSubjectType());
                                                scheduleSubject.setTeacherTitle(teacherData.getTeacherTitle());
                                                scheduleSubject.setTeacherName(teacherData.getTeacherName());
                                                scheduleSubject.setClassRoomName(teacherData.getClassName());
                                                scheduleSubject.setFirstIndex(start);
                                                scheduleSubject.setLastIndex(end);
                                                // to daje 15 min przerwy po przedmiocie
                                                i++;
                                            }
                                            break;
                                        } else equalsCounter = 0;
                                    }
                                    if (equalsCounter == 0 || equalsCounter == 6) {
                                        break;
                                    }
                                }
                            }
                            if (equalsCounter == 0 || equalsCounter == 6) {
                                break;
                            }

                        }
                        if (equalsCounter == 0 || equalsCounter == 6) {
                            break;
                        }
                    }


                    dayOfWeek.getSubjects().add(scheduleSubject);
                }
                semester.getDaysOfWeek().add(dayOfWeek);
            }
            schedule.getSemesters().add(semester);
        }

        return dao.postSchedule(schedule);
    }

    public Optional<Schedule> getScheduleById(String id) {
        return dao.getScheduleById(id);
    }

    public Optional<Schedule> deleteScheduleById(String id) {
        return dao.deleteScheduleById(id);
    }
}
