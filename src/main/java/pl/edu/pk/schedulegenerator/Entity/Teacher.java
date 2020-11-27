package pl.edu.pk.schedulegenerator.Entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection = "Teacher")
public class Teacher {

    @Id
    private String id;
    private String name;
    private String titleID;
    private String email;
    private String studyFieldId;
    private WeekAvailability availability;

}
