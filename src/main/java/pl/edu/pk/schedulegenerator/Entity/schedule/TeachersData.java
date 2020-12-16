package pl.edu.pk.schedulegenerator.Entity.schedule;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TeachersData {
    private String teacherName;
    private String teacherTitle;
    private String subjectName;
    private String subjectType;
    private String className;
}
