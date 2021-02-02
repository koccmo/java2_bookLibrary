package dental_clinic.core.requests.visit;

import dental_clinic.core.domain.*;

import java.util.Date;
import java.util.List;

public class AddVisitRequest {

    private Long id;
    private Visit visit;
    private Manipulation manipulation;


    public AddVisitRequest(Long id, Visit visit, Manipulation manipulation){
        this.id = id;
        this.visit = visit;
        this.manipulation = manipulation;
    }

    public Long getId() {
        return id;
    }

    public Integer getToothNumber() {
        return visit.getToothNumber();
    }

    public String getComment() {
        return visit.getComment();
    }

    public ToothStatus getToothStatus() {
        return visit.getToothStatus();
    }

    public Doctor getDoctor() {
        return visit.getDoctor();
    }

    public Manipulation getManipulation() {
        return manipulation;
    }

    public Date getDate() {
        return visit.getDate();
    }

}
