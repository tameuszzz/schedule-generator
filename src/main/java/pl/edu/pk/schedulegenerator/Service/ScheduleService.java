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

    public String postSchedule(AvailabilityData avaData) {

        Schedule schedule = new Schedule();
        schedule.setName(avaData.getName());
        schedule.setIfWinter(avaData.isIfWinter());
        schedule.setLessonWidth(avaData.getLessonWidth());
        schedule.setStudyFieldId(avaData.getStudyFieldId());
        schedule.setNumberOfSemester(avaData.getNumberOfSemester());

        ArrayList<ArrayList<ArrayList<TeachersData>>> preTeachersData = new ArrayList<>(avaData.getTeachersData());
        ArrayList<ArrayList<ArrayList<String>>> preClassData = new ArrayList<>(avaData.getClassroomsData());

        ArrayList<Semester> semesters = new ArrayList<>();

        int duration = avaData.getLessonWidth() * 3;
        int firstSem;
        int maxCompaction = 12;
        if (avaData.isIfWinter()) firstSem = 1;
        else firstSem = 2;

        boolean newSubjectFound = false, subjectFound = false, classroomFound = false;

        // petla iterujaca po liczbie semestrow
        for (int s = firstSem; s <= schedule.getNumberOfSemester(); s+=2) {

            Semester semester = new Semester();
            ArrayList<DayOfWeek> daysOfWeek = new ArrayList<>();
            ArrayList<HashMap<Integer, Integer>> compaction = new ArrayList<>();

            // petla iterujaca po dniach tygodnia
            for (int d = 0; d < 5; d++) {

                DayOfWeek dayOfWeek = new DayOfWeek();
                ArrayList<ScheduleSubject> subjects = new ArrayList<>();
                HashMap<Integer, Integer> dayCompaction = new HashMap<>();
                ArrayList<ArrayList<TeachersData>> preDailyTeachersData = preTeachersData.get(d);

                if (daysOfWeek.size() > d ) {
                    dayOfWeek = daysOfWeek.get(d);
                    subjects = daysOfWeek.get(d).getSubjects();
                    dayCompaction = compaction.get(d);
                    System.out.println(dayOfWeek);
                }

                // petla iterujaca po 15 min blokach
                for (int i = 0; i < 56; i++) {

                    // jesli nie ma w bloku zadnych przedmiotow -> pomin
                    if(preDailyTeachersData.get(i).isEmpty()) continue;

                    // jesli w danym bloku wartosc wpisanych przedmiotow jest za duza -> pomin
                    if (dayCompaction.getOrDefault(i, 0) >= 8) continue;

                    // petla iterujaca po zawartosci blokow 15 min
                    for(int k = 0; k < preDailyTeachersData.get(i).size(); k++) {
                        // k-ty element teachersData

                        TeachersData teachersData = preDailyTeachersData.get(i).get(k);

                        // sprawdzenie czy wybrany przedmiot nalezy do obecnego semestru
                        if(teachersData.getSemesterOfSubject() != s) continue;

                        // obliczenie warosci przedmiotu
                        int valueOfSubject = calculateValueOfSubject(teachersData.getSubjectType());

                        // jak suma wartosci wpisanych juz przedmiotow w bloku i przedmiotu wybranego jest za duza -> pomin
                        if (dayCompaction.getOrDefault(i, 0) + valueOfSubject > 8) continue;
                        subjectFound = false;

                        // petla iterujaca X kolejnych blokow 15 min w celu sprawdzenia czy jest miejsce na wpisanie przedmiotu
                        for(int j = i; j < i+duration; ++j) {
                            // petla sprawdza czy w danym bloku znajduje sie wybrany przedmiot
                            for(int m = 0; m < preDailyTeachersData.get(j).size(); m++) {
                                if (teachersData.equals(preDailyTeachersData.get(j).get(m))) {
                                    subjectFound = true;
                                    break;
                                } else subjectFound = false;
                            }
                        }

                        if(subjectFound) {
                            if (teachersData.getClassName() == null) {
                                subjects.add(createSubject(teachersData, i, duration));
                            }
                            else if (teachersData.getClassName().isEmpty()) {
                                subjects.add(createSubject(teachersData, i, duration));
                            }
                            else {
                                ArrayList<ArrayList<String>> preDailyClassData = preClassData.get(d);
                                for(int classIndex = 0; classIndex < teachersData.getClassName().size(); classIndex++) {
                                    String classFoundName = teachersData.getClassName().get(classIndex);
                                    classroomFound = false;
                                    for(int j = i; j < i+duration; ++j) {
                                        for(int m = 0; m < preDailyClassData.get(j).size(); m++) {
                                            if (classFoundName.equals(preDailyClassData.get(j).get(m))) {
                                                classroomFound = true;
                                                break;
                                            }
                                            else classroomFound = false;
                                        }
                                        if (!classroomFound) break;
                                    }
                                    if (!classroomFound) continue;
                                    subjects.add(createSubject(teachersData, i, duration,  classFoundName));
                                    removeClassroomFromClassroomsData(avaData, classFoundName, i, duration, d);
                                    break;
                                }
                                if (!classroomFound) continue;
                            }

                            log.info("Subject found: " + teachersData.toString());
                            newSubjectFound = true;

                            // to robi przerwe po zajeciach
                            if(!preDailyTeachersData.get(i+duration).isEmpty()) preDailyTeachersData.get(i+duration).clear();

                            removeSubjectFromTeachersData(preTeachersData, teachersData);
                            preTeachersData.set(d, removeTeacherFromTeachersData(i, duration, preDailyTeachersData, teachersData));
                            preTeachersData.set(d, removeGroupsFromTeachersData(i, duration, preDailyTeachersData, teachersData));

                            for (int j = i; j < i+duration; j++) {
                                int tmpDayCompaction = dayCompaction.getOrDefault(j, 0);
                                dayCompaction.put(j, tmpDayCompaction + valueOfSubject);
                            }

                            i = -1;
                            break;
                        }
                    }

                    if(computeCompation(dayCompaction, duration) > maxCompaction) break;

                }

                if(!(subjects == null) && !subjects.isEmpty()) {
                    dayOfWeek.setSubjects(subjects);
                }

                compaction.add(dayCompaction);

                if (d >= daysOfWeek.size()) {
                    daysOfWeek.add(dayOfWeek);
                } else {
                    daysOfWeek.set(d, dayOfWeek);
                }


                if ((d == 4 && !preTeachersData.isEmpty()) && newSubjectFound) {
                        d = -1;
                        maxCompaction +=5;
                        newSubjectFound = false;
                }

            }

            semester.setDaysOfWeek(daysOfWeek);
            semesters.add(semester);

        }

        schedule.setSemesters(semesters);

        return dao.postSchedule(schedule);
    }

    private int computeCompation(HashMap<Integer, Integer> dayCompaction, int duration) {
        int sum = 0;
        for (Integer n : dayCompaction.values()) {
            sum += n;
        }
        return sum / duration;
    }

    private ArrayList<ArrayList<TeachersData>> removeTeacherFromTeachersData(int i, int duration, ArrayList<ArrayList<TeachersData>> data, TeachersData teachersData) {
        for (int j = i; j < i+duration; j++) {
            data.get(j).removeIf(n -> n.getTeacherName().equals(teachersData.getTeacherName()));
        }
        return data;
    }

    private ArrayList<ArrayList<TeachersData>> removeGroupsFromTeachersData(int i, int duration, ArrayList<ArrayList<TeachersData>> data, TeachersData teachersData) {

        final String type = teachersData.getSubjectType();
        final int gruop = Integer.parseInt(teachersData.getGroup());

        for (int j = i; j < i+duration; j++) {

            if(type.equals("Exercise") || type.equals("Seminars")) {
                data.get(j).removeIf(n -> n.getGroup().equals(teachersData.getGroup())
                        && n.getSubjectType().equals(teachersData.getSubjectType()));
                data.get(j).removeIf(n -> (Integer.parseInt(n.getGroup()) == (gruop * 2)
                        || Integer.parseInt(n.getGroup()) == (gruop * 2 - 1))
                        && n.getSubjectType().equals("Laboratories"));
            }
            else if (type.equals("Laboratories")) {

                int excerciseGroup = 0;

                switch (gruop) {
                    case 1:
                    case 2:
                        excerciseGroup = 1;
                        break;
                    case 3:
                    case 4:
                        excerciseGroup = 2;
                        break;
                    case 5:
                    case 6:
                        excerciseGroup = 3;
                        break;
                    case 7:
                    case 8:
                        excerciseGroup = 4;
                        break;

                }
                data.get(j).removeIf(n -> n.getGroup().equals(teachersData.getGroup())
                        && n.getSubjectType().equals(teachersData.getSubjectType()));

                int finalExcerciseGroup = excerciseGroup;
                data.get(j).removeIf(n -> (Integer.parseInt(n.getGroup()) == finalExcerciseGroup
                        && (n.getSubjectType().equals("Exercise")) || n.getSubjectType().equals("Seminars")));
            }
        }

        return data;
    }

    private int calculateValueOfSubject(String subjectType) {
        switch (subjectType) {
            case "Laboratories":
            case "Exercise":
            case "Seminars":
                return 4;
            default:
                return 8;
        }
    }

    private ScheduleSubject createSubject(TeachersData teachersData, int i, int duration, String className) {
        ScheduleSubject scheduleSubject = new ScheduleSubject();
        scheduleSubject.setSubjectName(teachersData.getSubjectName());
        scheduleSubject.setSubjectType(teachersData.getSubjectType());
        scheduleSubject.setTeacherTitle(teachersData.getTeacherTitle());
        scheduleSubject.setTeacherName(teachersData.getTeacherName());
        scheduleSubject.setGroup(teachersData.getGroup());
        scheduleSubject.setClassRoomName(className);
        scheduleSubject.setFirstIndex(i);
        scheduleSubject.setLastIndex(i + duration - 1);
        return scheduleSubject;
    }

    private ScheduleSubject createSubject(TeachersData teachersData, int i, int duration) {
        return  createSubject(teachersData, i, duration, "online");
    }

    private void removeSubjectFromTeachersData(ArrayList<ArrayList<ArrayList<TeachersData>>> data, TeachersData teachersData) {
        for (ArrayList<ArrayList<TeachersData>> dailyData : data) {
            for (ArrayList<TeachersData> teachersDataArrayList : dailyData) {
                teachersDataArrayList.removeIf(n -> n.equals(teachersData));
            }
        }
    }

    private void removeClassroomFromClassroomsData(AvailabilityData avaData, String classFoundName, int i, int duration, int d) {
        for(int j = i; j < i+duration; ++j) {
            for(int m = 0; m < avaData.getClassroomsData().get(d).get(j).size(); m++) {
                if (classFoundName.equals(avaData.getClassroomsData().get(d).get(j).get(m))) {
                    avaData.getClassroomsData().get(d).get(j).remove(m);
                }
            }
        }
    }

    public Optional<Schedule> getScheduleById(String id) {
        return dao.getScheduleById(id);
    }

    public Optional<Schedule> deleteScheduleById(String id) {
        return dao.deleteScheduleById(id);
    }

    public Optional<Schedule> updateScheduleById(String id, ScheduleUpdate scheduleUpdate) {
        return dao.updateScheduleById(id, scheduleUpdate);
    }
}
