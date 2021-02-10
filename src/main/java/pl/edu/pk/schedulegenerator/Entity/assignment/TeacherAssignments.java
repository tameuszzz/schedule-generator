package pl.edu.pk.schedulegenerator.Entity.assignment;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TeacherAssignments {
    private String teacherId;
    private String subjectName;
    private int lecturesGroups;
    private int exerciseGroups;
    private int laboratoriesGroups;
    private int seminarsGroups;
}
