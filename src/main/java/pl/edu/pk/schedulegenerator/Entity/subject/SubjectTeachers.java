package pl.edu.pk.schedulegenerator.Entity.subject;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.edu.pk.schedulegenerator.Entity.Teacher;

@Data
@RequiredArgsConstructor
public class SubjectTeachers {

    private Teacher teacher;
    private boolean lecturesEnable;
    private int lecturesHours;
    //private int lecturesGroups;
    private boolean exerciseEnable;
    private int exerciseHours;
    //private int exerciseGroups;
    private boolean laboratoriesEnable;
    private int laboratoriesHours;
    //private int laboratoriesGroups;
    private boolean seminarsEnable;
    private int seminarsHours;
    //private int seminarsGroups;
}
