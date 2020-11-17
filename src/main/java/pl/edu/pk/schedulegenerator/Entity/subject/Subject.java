package pl.edu.pk.schedulegenerator.Entity.subject;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

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
    private ArrayList<SubjectTeachers> teachers;

}
