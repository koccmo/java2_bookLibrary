package dental_clinic.core.domain;

import java.util.GregorianCalendar;

public class PlannedVisit {

    private Long id;
    private GregorianCalendar visitTime;
    private PersonalData personalData;

    public PlannedVisit(GregorianCalendar visitTime, PersonalData personalData) {
        this.visitTime = visitTime;
        this.personalData = personalData;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setVisitTime(GregorianCalendar visitTime) {
        this.visitTime = visitTime;
    }

    public GregorianCalendar getVisitTime() {
        return  visitTime;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }
}
