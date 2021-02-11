package dental_clinic.core.requests.plannedVisit;

public class SearchPlannedVisitsByDateRequest {

    private int dayFrom;
    private int dayTo;
    private int monthFrom;
    private int monthTo;

    public SearchPlannedVisitsByDateRequest() { }

    public SearchPlannedVisitsByDateRequest(int dayFrom, int dayTo, int monthFrom, int monthTo) {
        this.dayFrom = dayFrom;
        this.dayTo = dayTo;
        this.monthFrom = monthFrom;
        this.monthTo = monthTo;
    }

    public int getDayFrom() {
        return dayFrom;
    }

    public int getDayTo() {
        return dayTo;
    }

    public int getMonthFrom() {
        return monthFrom;
    }

    public int getMonthTo() {
        return monthTo;
    }

    public void setDayFrom(int dayFrom) {
        this.dayFrom = dayFrom;
    }

    public void setDayTo(int dayTo) {
        this.dayTo = dayTo;
    }

    public void setMonthFrom(int monthFrom) {
        this.monthFrom = monthFrom;
    }

    public void setMonthTo(int monthTo) {
        this.monthTo = monthTo;
    }
}
