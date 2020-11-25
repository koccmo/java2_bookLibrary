package dental_clinic.core.requests;

import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.domain.Visit;

import java.util.Optional;

public class AddVisitRequest {

    private long id;
    private Visit visit;


    public AddVisitRequest(long id, Visit visit){
        this.id = id;
        this.visit = visit;
    }

    public long getId() {
        return id;
    }

    public int getToothNumber() {
        return visit.getToothNumber();
    }

    public Optional<String> getComment() {
        return visit.getComment();
    }

    public ToothStatus getToothStatus() {
        return visit.getToothStatus();
    }

    public String getDoctor() {
        return visit.getDoctor();
    }
}
