package pl.edu.pk.schedulegenerator.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pk.schedulegenerator.DAO.ScheduleDAO;
import pl.edu.pk.schedulegenerator.Entity.schedule.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

@Slf4j
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

        ArrayList<Semester> semesters = new ArrayList<>();

        int firstSem;
        if (avaData.isIfWinter()) firstSem = 1;
        else firstSem = 2;

        boolean subjectFound = false, classroomFound = false;

        for (int s = firstSem; s <= schedule.getNumberOfSemester(); s+=2) {
            Semester semester = new Semester();
            ArrayList<DayOfWeek> daysOfWeek = new ArrayList<>();
            ArrayList<HashMap<Integer, Integer>> compaction = new ArrayList<>();
            for (int d = 0; d < 5; d++) {

                DayOfWeek dayOfWeek = new DayOfWeek();
                ArrayList<ScheduleSubject> subjects = new ArrayList<>();
                HashMap<Integer, Integer> dayCompaction = new HashMap<>();

                for (int i = 0; i < 56; i++) {

                    //System.out.println(i + ", " + dayCompaction.getOrDefault(i, 0));

                    if (dayCompaction.getOrDefault(i, 0) >= 8) continue;

                    for(int k = 0; k < avaData.getTeachersData().get(d).get(i).size(); k++) {
                        TeachersData teachersData = avaData.getTeachersData().get(d).get(i).get(k);
                        if(teachersData.getSemesterOfSubject() != s) continue;
                        int valueOfSubject = calculateValueOfSubject(teachersData.getSubjectType());
                        //System.out.println("valueOfSubject: " + valueOfSubject);
                        if (dayCompaction.getOrDefault(i, 0) + valueOfSubject > 8) continue;
                        subjectFound = false;
                        for(int j = i; j < i+6; ++j) {
                            for(int m = 0; m < avaData.getTeachersData().get(d).get(j).size(); m++) {
                                if (teachersData.equals(avaData.getTeachersData().get(d).get(j).get(m))) {
                                    subjectFound = true;
                                    break;
                                } else subjectFound = false;
                            }
                            //System.out.println("k = " + k + ", i = " + i);
                            //System.out.println(subjectFound);
                        }
                        if(subjectFound) {
                            log.info("Subject found: " + teachersData.toString());
                            if (teachersData.getClassName() == null) {
                                subjects.add(createSubject(teachersData, i));
                                //updateDayCompaction(i, valueOfSubject, dayCompaction);
                            }
                            //else if (teachersData.getClassName().isEmpty()) {
                            else {
                                subjects.add(createSubject(teachersData, i));
                                //updateDayCompaction(i, valueOfSubject, dayCompaction);
                            }
//                            else {
//                                // TODO: Wyszukiwanie sal
//                                for(int classIndex = 0; classIndex < teachersData.getClassName().size(); classIndex++) {
//                                    String classFoundName = teachersData.getClassName().get(classIndex);
//                                    classroomFound = false;
//                                    for(int j = i; j < i+6; ++j) {
//                                        for(int m = 0; m < avaData.getClassroomsData().get(d).get(j).size(); m++) {
//                                            if (classFoundName.equals(avaData.getClassroomsData().get(d).get(j).get(m))) {
//                                                classroomFound = true;
//                                                break;
//                                            }
//                                            else classroomFound = false;
//                                        }
//                                        if (!classroomFound) break;
//                                    }
//                                    if (classroomFound) {
//                                        subjects.add(createSubject(teachersData, i));
//                                        removeClassroomFromClassroomsData(avaData, classFoundName, i, d);
//                                    }
//                                }
//                                if (!classroomFound) continue;
//                            }
                            removeSubjectFromTeachersData(avaData, teachersData);
                            removeTeacherFromTeachersData(i, d, avaData, teachersData);
                            for (int j = i; j < i+6; j++) {
                                int tmpDayCompaction = dayCompaction.getOrDefault(j, 0);
                                dayCompaction.put(j, tmpDayCompaction + valueOfSubject);
                            }
//                            if(valueOfSubject == 8) {
//                                for(int j = i; j < i+6; j++) {
//                                    for (int m = 0; m < avaData.getTeachersData().get(d).get(j).size(); m++)
//                                    avaData.getTeachersData().get(d).get(j).remove(m);
//                                }
//                            } else {
//                                for(int j = i; j < i+6; j++) {
//                                    for (int m = 0; m < avaData.getTeachersData().get(d).get(j).size(); m++)
//                                        if(avaData.getTeachersData().get(d).get(j).get(m).getSubjectType().equals("Lectures"))
//                                            avaData.getTeachersData().get(d).get(j).remove(m);
//                                }
//                            }
                            i = 0;
                            break;
                        }
                    }

                }

                if(!subjects.isEmpty()) {
                    dayOfWeek.setSubjects(subjects);
                }

                //System.out.println(dayCompaction);
                daysOfWeek.add(dayOfWeek);

            }

            semester.setDaysOfWeek(daysOfWeek);
            semesters.add(semester);

        }

        schedule.setSemesters(semesters);

        System.out.println(avaData);

        return dao.postSchedule(schedule);
    }

    private void removeTeacherFromTeachersData(int i, int d, AvailabilityData avaData, TeachersData teachersData) {
        for (int j = i; j < i+6; j++) {
            for(int m = 0; m < avaData.getTeachersData().get(d).get(j).size(); m++) {
                if(teachersData.getTeacherName().equals(avaData.getTeachersData().get(d).get(j).get(m).getTeacherName())
                && teachersData.getTeacherTitle().equals(avaData.getTeachersData().get(d).get(j).get(m).getTeacherTitle())) {
                    avaData.getTeachersData().get(d).get(j).remove(m);
                    System.out.println("removing : " + teachersData.getTeacherTitle() + " " + teachersData.getTeacherName());
                }
            }
        }
    }

    private void updateDayCompaction(int i, int valueOfSubject, HashMap<Integer, Integer> dayCompaction) {
        for (int j = i; j < i+6; j++) {
            int tmpDayCompaction = dayCompaction.getOrDefault(i, 0);
            dayCompaction.put(i, tmpDayCompaction + valueOfSubject);
        }
    }

    private int calculateValueOfSubject(String subjectType) {
        switch (subjectType) {
            case "Exercise":
            case "Seminars":
                return 2;
            case "Laboratories":
                return 1;
            default:
                return 8;
        }
    }

    private ScheduleSubject createSubject(TeachersData teachersData, int i) {
        ScheduleSubject scheduleSubject = new ScheduleSubject();
        scheduleSubject.setSubjectName(teachersData.getSubjectName());
        scheduleSubject.setSubjectType(teachersData.getSubjectType());
        scheduleSubject.setTeacherTitle(teachersData.getTeacherTitle());
        scheduleSubject.setTeacherName(teachersData.getTeacherName());
        scheduleSubject.setFirstIndex(i);
        scheduleSubject.setLastIndex(i+5);
        return scheduleSubject;
    }

    private void removeSubjectFromTeachersData(AvailabilityData avaData, TeachersData teachersData) {
        for(int i = 0; i < avaData.getTeachersData().size(); i++) {
            for (int j = 0; j < avaData.getTeachersData().get(i).size(); j++) {
                for (int k = 0; k < avaData.getTeachersData().get(i).get(j).size(); k ++) {
                    if( avaData.getTeachersData().get(i).get(j).get(k).equals(teachersData)) {
                        avaData.getTeachersData().get(i).get(j).remove(k);
                    }
                }
            }
        }
        System.out.println("usuwam przedmiot: " + teachersData);
    }

    private void removeClassroomFromClassroomsData(AvailabilityData avaData, String classFoundName, int i, int d) {
        for(int j = i; j < i+6; ++j) {
            for(int m = 0; m < avaData.getClassroomsData().get(d).get(j).size(); m++) {
                if (classFoundName.equals(avaData.getClassroomsData().get(d).get(j).get(m))) {
                    avaData.getClassroomsData().get(d).get(j).remove(m);
                }
            }
        }
        System.out.println("usuwam sale: " + classFoundName + "z dnia: " + d + " [od]:[do] " + i + ":" + (i+5));
    }

    public Optional<Schedule> getScheduleById(String id) {
        return dao.getScheduleById(id);
    }

    public Optional<Schedule> deleteScheduleById(String id) {
        return dao.deleteScheduleById(id);
    }
}
