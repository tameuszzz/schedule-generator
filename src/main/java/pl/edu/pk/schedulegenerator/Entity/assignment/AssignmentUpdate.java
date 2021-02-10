package pl.edu.pk.schedulegenerator.Entity.assignment;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class AssignmentUpdate {
    private String name;
    private boolean ifWinter;
    private ArrayList<ArrayList<ArrayList<Integer>>> groups;
    private ArrayList<TeacherAssignments> assignments;
}
