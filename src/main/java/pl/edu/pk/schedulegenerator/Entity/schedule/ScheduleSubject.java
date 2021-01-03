package pl.edu.pk.schedulegenerator.Entity.schedule;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ScheduleSubject {
    private int firstIndex;
    private int lastIndex;
    private String subjectName;
    private String subjectType;
    private String teacherTitle;
    private String teacherName;
    private String classRoomName;
    private String group;
}
