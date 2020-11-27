package pl.edu.pk.schedulegenerator.Entity;

import lombok.Data;

@Data
public class TeacherUpdate {
    private String name;
    private String titleID;
    private String email;
    private String studyFieldId;
    private WeekAvailability availability;
}
