package pl.edu.pk.schedulegenerator.Entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
@Document(collection = "Teacher")
public class Teacher {

    @Id
    private String id;

    @NotBlank
//    @Size(min = 1, message = "Imię i nazwisko musi zawierać minimum 5 znaków.")
    private String name;

    @NotBlank(message = "Stopień/tytuł naukowy nie został podany.")
    private String titleID;

    @NotBlank(message = "Adres email nie został podany.")
    @Email(message = "Adres email jest niepoprawny.")
    private String email;

    @NotBlank(message = "Numer ID kierunku studiów nie został podany.")
    private String studyFieldId;

    @NotNull(message = "Dyspozycyjność nauczyciela nie została podana.")
    private WeekAvailability availability;

}
