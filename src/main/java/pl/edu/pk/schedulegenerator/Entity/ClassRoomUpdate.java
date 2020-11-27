package pl.edu.pk.schedulegenerator.Entity;

import lombok.Data;

@Data
public class ClassRoomUpdate {
    private String name;
    private WeekAvailability availability;
}
