package pl.edu.pk.schedulegenerator.Entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ClassRoomUpdate {
    @NotBlank(message = "Nazwa sali nie została podana")
    private String name;

    @NotNull(message = "Dyspozycyjność sali nie została podana")
    private WeekAvailability availability;
}
