package pl.edu.pk.schedulegenerator.model.subject;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection = "Subject")
public class Subject {

    @Id
    private String id;
    private String name;
    private boolean eligibility;
    private SubjectSchedule schedule;
    private StudyStudents students;
    private SubjectTeachers teachers;

}
