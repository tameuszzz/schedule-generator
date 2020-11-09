package pl.edu.pk.schedulegenerator.model.subject;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ScheduleActivities {

    private boolean enabled = false;
    private int hours = 0;

    public ScheduleActivities(boolean enabled, int hours) {
        this.enabled = enabled;
        this.hours = hours;
    }
}
