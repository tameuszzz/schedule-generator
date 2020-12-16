package pl.edu.pk.schedulegenerator.Entity.schedule;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class Semester {
    private ArrayList<DayOfWeek> daysOfWeek;
}
