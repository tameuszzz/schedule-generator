package pl.edu.pk.schedulegenerator.Entity.subject;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.edu.pk.schedulegenerator.Entity.Teacher;

@Data
@RequiredArgsConstructor
public class SubjectTeachers {
    private Teacher teacher;
    private boolean lecturesEnable;
    private boolean exerciseEnable;
    private boolean laboratoriesEnable;
    private boolean seminarsEnable;
}
