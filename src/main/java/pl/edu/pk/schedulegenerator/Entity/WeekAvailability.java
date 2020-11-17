package pl.edu.pk.schedulegenerator.Entity;

public class WeekAvailability {

    private boolean oneWeek;
    private Availability allWeeks;
    private Availability evenWeeks;
    private Availability oddWeeks;

    public WeekAvailability(boolean oneWeek, Availability allWeeks, Availability evenWeeks, Availability oddWeeks) {
        if(oneWeek) {
            this.oneWeek = true;
            this.allWeeks = allWeeks;
            this.evenWeeks = new Availability();
            this.oddWeeks = new Availability();
        } else {
            this.oneWeek = false;
            this.allWeeks = new Availability();
            this.evenWeeks = evenWeeks;
            this.oddWeeks = oddWeeks;
        }
    }

    public boolean isOneWeek() {
        return oneWeek;
    }

    public void setOneWeek(boolean oneWeek) {
        this.oneWeek = oneWeek;
    }

    public Availability getAllWeeks() {
        return allWeeks;
    }

    public void setAllWeeks(Availability allWeeks) {
        this.allWeeks = allWeeks;
    }

    public Availability getEvenWeeks() {
        return evenWeeks;
    }

    public void setEvenWeeks(Availability evenWeeks) {
        this.evenWeeks = evenWeeks;
    }

    public Availability getOddWeeks() {
        return oddWeeks;
    }

    public void setOddWeeks(Availability oddWeeks) {
        this.oddWeeks = oddWeeks;
    }
}