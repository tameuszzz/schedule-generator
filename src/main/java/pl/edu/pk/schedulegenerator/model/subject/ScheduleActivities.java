package pl.edu.pk.schedulegenerator.model.subject;

import lombok.Data;

@Data
public class ScheduleActivities {

    private boolean IfEnabled = false;
    private int hours = 0;

    public ScheduleActivities(boolean ifEnabled, int hours) {
        this.IfEnabled = ifEnabled;
        this.hours = hours;
    }
}
