package pl.edu.pk.schedulegenerator.Entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
@Document(collection = "ClassRoom")
public class ClassRoom {

    @Id
    private String id;

    @NotBlank(message = "Nazwa sali nie została podana")
    private String name;

    @NotNull(message = "Dyspozycyjność sali nie została podana")
    private WeekAvailability availability;

}
