package pl.edu.pk.schedulegenerator.Entity.schedule;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class TeachersData {
    private String teacherName;
    private String teacherTitle;
    private int semesterOfSubject;
    private String subjectName;
    private String subjectType;
    private String group;
    private ArrayList<String> className;
}
