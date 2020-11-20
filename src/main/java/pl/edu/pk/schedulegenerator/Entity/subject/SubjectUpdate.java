package pl.edu.pk.schedulegenerator.Entity.subject;

import lombok.Data;

import java.util.ArrayList;

@Data
public class SubjectUpdate {
    private String name;
    private boolean eligibility;
    private SubjectSchedule schedule;
    private StudyStudents students;
    private ArrayList<SubjectTeachers> teachers;
}
