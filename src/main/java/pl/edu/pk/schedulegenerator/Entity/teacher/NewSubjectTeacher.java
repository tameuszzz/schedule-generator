package pl.edu.pk.schedulegenerator.Entity.teacher;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NewSubjectTeacher {
    private String teacherId;
    private String subjectName;
    private boolean lecturesEnable;
    private int lecturesHours;
    private boolean exerciseEnable;
    private int exerciseHours;
    private boolean laboratoriesEnable;
    private int laboratoriesHours;
    private boolean seminarsEnable;
    private int seminarsHours;

}