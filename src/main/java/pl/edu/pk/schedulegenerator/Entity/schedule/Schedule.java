package pl.edu.pk.schedulegenerator.Entity.schedule;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
@Document(collection = "Schedule")
public class Schedule {

    @Id
    private String id;
    private String name;
    private boolean ifWinter;
    private String studyFieldId;
    private int numberOfSemester;
    private ArrayList<Semester> semesters;
}
