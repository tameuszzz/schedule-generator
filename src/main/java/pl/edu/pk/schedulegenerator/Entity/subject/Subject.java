package pl.edu.pk.schedulegenerator.Entity.subject;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Data
@RequiredArgsConstructor
@Document(collection = "Subject")
public class Subject {

    @Id
    private String id;

    @NotBlank(message = "Nazwa przedmiotu nie została podana.")
//    @Size(min=3, message = "Nazwa przedmiotu musi mieć co najmniej 3 znaki.")
    private String name;

    @NotNull(message = "Obieralność przedmiotu nie została podana.")
    private boolean eligibility;

    private float rate;

    private SubjectSchedule schedule;
    private StudyStudents students;
    private String error = "";

    private ArrayList<SubjectTeachers> teachers;

}
