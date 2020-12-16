package pl.edu.pk.schedulegenerator.Entity.schedule;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class AvailabilityData {

    private String name;
    private boolean ifWinter;
    private String studyFieldId;
    private int numberOfSemester;
    private ArrayList<ArrayList<String>> classroomsData;
    private ArrayList<ArrayList<TeachersData>> teachersData;
}