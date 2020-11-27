package pl.edu.pk.schedulegenerator.Entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection = "StudyField")
public class StudyField {

    @Id
    private String id;
    private String name;
    private int degree;
    private int numberOfSemesters;

}
