package dental_clinic.core.requests.plannedVisit;

public class SearchPlannedVisitsByDateRequest {

    private int dayFrom;
    private int dayTo;
    private int monthFrom;
    private int monthTo;

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
}
