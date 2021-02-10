package pl.edu.pk.schedulegenerator.Entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Data
@RequiredArgsConstructor
@Document(collection = "StudyField")
public class StudyField {

    @Id
    private String id;

    @NotBlank(message = "Nazwa kierunku nie została podana.")
    //@Size(min = 3, message = "Nazwa kierunku musi mieć co najmniej 3 znaki.")
    private String name;

    @NotNull(message = "Nie podano stopnia kierunku studiów.")
    @Min(value = 1, message = "Stopnień studiów może mieć wartość od 1 do 4.")
    @Max(value = 4, message = "Stopnień studiów może mieć wartość od 1 do 4.")
    private int degree;

    @NotNull(message = "Liczba semestrów nie została podana.")
    @Min(value = 1, message = "Podano zbyt mała liczbę semestrów")
    @Max(value = 10, message = "Podano zbyt dużą liczbę semestrów")
    private int numberOfSemesters;

    private int fullTime;
    private int erasmus;

    public StudyField(String id, String name, int degree, int numberOfSemesters) {
        this.id = id;
        this.name = name;
        this.degree = degree;
        this.numberOfSemesters = numberOfSemesters;
    }
}
