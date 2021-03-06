package pl.edu.pk.schedulegenerator.Entity;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class StudyFieldUpdate {

    @NotBlank(message = "Nazwa kierunku nie została podana.")
//    @Size(min=1, message = "Nazwa musi mieć co najmniej 3 znaki.")
    private String name;

    @NotNull(message = "Nie podano stopnia kierunku studiów.")
    @Min(value = 1, message = "Stopnień studiów może mieć wartość od 1 do 4.")
    @Max(value = 4, message = "Stopnień studiów może mieć wartość od 1 do 4.")
    private int degree;

    @NotNull(message = "Liczba semestrów nie została podana.")
    @Min(value = 1, message = "Podano zbyt mała liczbę semestrów")
    @Max(value = 10, message = "Podano zbyt dużą liczbę semestrów")
    private int numberOfSemesters;
}
