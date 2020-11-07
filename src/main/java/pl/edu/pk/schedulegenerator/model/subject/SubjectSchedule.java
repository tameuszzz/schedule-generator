package pl.edu.pk.schedulegenerator.model.subject;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SubjectSchedule {

    private ScheduleActivities lectures;
    private ScheduleActivities exercise;
    private ScheduleActivities laboratories;
    private ScheduleActivities seminars;
}
