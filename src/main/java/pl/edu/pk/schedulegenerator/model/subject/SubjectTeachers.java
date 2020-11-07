package pl.edu.pk.schedulegenerator.model.subject;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SubjectTeachers {
    private String teacherId;
    private boolean lecturesEnable;
    private boolean exerciseEnable;
    private boolean laboratoriesEnable;
    private boolean seminarsEnable;
}
