package pl.edu.pk.schedulegenerator.Entity.teacher;

import lombok.Data;
import pl.edu.pk.schedulegenerator.Entity.WeekAvailability;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TeacherUpdate {
    @NotBlank
//    @Size(min = 1, message = "Imię i nazwisko musi zawierać minimum 5 znaków.")
    private String name;

    @NotBlank(message = "Stopień/tytuł naukowy nie został podany.")
    private String titleID;

    @NotBlank(message = "Adres email nie został podany.")
    @Email(message = "Adres email jest niepoprawny.")
    private String email;

    private int hours;

    private HoursByType hoursByType;

    @NotBlank(message = "Numer ID kierunku studiów nie został podany.")
    private String studyFieldId;

    @NotNull(message = "Dyspozycyjność nauczyciela nie została podana.")
    private WeekAvailability availability;

    public TeacherUpdate() {
    }

    public TeacherUpdate(String name, String titleID, String email, int hours, HoursByType hoursByType, String studyFieldId, WeekAvailability availability) {
        this.name = name;
        this.titleID = titleID;
        this.email = email;
        this.hours = hours;
        this.hoursByType = hoursByType;
        this.studyFieldId = studyFieldId;
        this.availability = availability;
    }

    public TeacherUpdate(@NotBlank String name, @NotBlank(message = "Stopień/tytuł naukowy nie został podany.") String titleID, @NotBlank(message = "Adres email nie został podany.") @Email(message = "Adres email jest niepoprawny.") String email, int hours, @NotBlank(message = "Numer ID kierunku studiów nie został podany.") String studyFieldId, @NotNull(message = "Dyspozycyjność nauczyciela nie została podana.") WeekAvailability availability) {
        this.name = name;
        this.titleID = titleID;
        this.email = email;
        this.hours = hours;
        this.hoursByType = new HoursByType();
        this.studyFieldId = studyFieldId;
        this.availability = availability;
    }
}
