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
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    @Min(1)
    @Max(2)
    private int degree;
    @NotNull
    @Min(1)
    @Max(10)
    private int numberOfSemesters;
    @NotBlank
    private String departmentID;

}
