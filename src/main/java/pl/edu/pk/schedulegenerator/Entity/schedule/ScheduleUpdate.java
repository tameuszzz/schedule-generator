package pl.edu.pk.schedulegenerator.Entity.schedule;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class ScheduleUpdate {

    private String name;
    private boolean ifWinter;
    private String studyFieldId;
    private int numberOfSemester;
    private int lessonWidth;
    private ArrayList<Semester> semesters;

    public void addSemester(Semester semester) {
        this.semesters.add(semester);
    }

}
