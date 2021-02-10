package pl.edu.pk.schedulegenerator.Entity.assignment;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
@Document(collection = "Assignment")
public class Assignment {

    @Id
    private String id;
    private String name;
    private boolean ifWinter;
    private ArrayList<ArrayList<ArrayList<Integer>>> groups;
    private ArrayList<TeacherAssignments> assignments;

}
