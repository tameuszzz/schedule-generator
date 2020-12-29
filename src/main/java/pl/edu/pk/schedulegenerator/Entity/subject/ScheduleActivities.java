package pl.edu.pk.schedulegenerator.Entity.subject;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class ScheduleActivities {

    private boolean enabled = false;
    //private int hours = 0;
    private int groups;
    private boolean online;
    private ArrayList<String> classroom;
}
