package pl.edu.pk.schedulegenerator.Entity.teacher;

import lombok.Data;

@Data
public class HoursByType {
    private int hoursCurr = 0;
    private int hoursIstZ = 0;
    private int hoursIstL = 0;
    private int hoursIIstL = 0;
    private int hoursIIstZ = 0;
    private int hoursInstZ = 0;
    private int hoursInstL = 0;
    private int hoursIInstZ = 0;
    private int hoursIInstL = 0;

    public HoursByType() {
        this.hoursCurr = 0;
        this.hoursIstZ = 0;
        this.hoursIstL = 0;
        this.hoursIIstL = 0;
        this.hoursIIstZ = 0;
        this.hoursInstZ = 0;
        this.hoursInstL = 0;
        this.hoursIInstZ = 0;
        this.hoursIInstL = 0;
    }

    public HoursByType(int hoursCurr, int hoursIstZ, int hoursIstL, int hoursIIstL, int hoursIIstZ, int hoursInstZ, int hoursInstL, int hoursIInstZ, int hoursIInstL) {
        this.hoursCurr = hoursCurr;
        this.hoursIstZ = hoursIstZ;
        this.hoursIstL = hoursIstL;
        this.hoursIIstL = hoursIIstL;
        this.hoursIIstZ = hoursIIstZ;
        this.hoursInstZ = hoursInstZ;
        this.hoursInstL = hoursInstL;
        this.hoursIInstZ = hoursIInstZ;
        this.hoursIInstL = hoursIInstL;
    }
}
