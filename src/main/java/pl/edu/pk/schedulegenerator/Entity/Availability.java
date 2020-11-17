package pl.edu.pk.schedulegenerator.Entity;

public class Availability {

    private boolean[] monday;
    private boolean[] tuesday;
    private boolean[] wednesday;
    private boolean[] thursday;
    private boolean[] friday;

    public Availability(boolean[] monday, boolean[] tuesday, boolean[] wednesday, boolean[] thursday, boolean[] friday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
    }

    public Availability() {
    }

    public boolean[] getMonday() {
        return monday;
    }

    public void setMonday(boolean[] monday) {
        this.monday = monday;
    }

    public boolean[] getTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean[] tuesday) {
        this.tuesday = tuesday;
    }

    public boolean[] getWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean[] wednesday) {
        this.wednesday = wednesday;
    }

    public boolean[] getThursday() {
        return thursday;
    }

    public void setThursday(boolean[] thursday) {
        this.thursday = thursday;
    }

    public boolean[] getFriday() {
        return friday;
    }

    public void setFriday(boolean[] friday) {
        this.friday = friday;
    }
}
