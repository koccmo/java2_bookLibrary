package dental_clinic.core.requests;

import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.domain.Visit;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class AddVisitRequest {

    private Long id;
    private Visit visit;


    public AddVisitRequest(Long id, Visit visit){
        this.id = id;
        this.visit = visit;
    }

    public Long getId() {
        return id;
    }

    public Integer getToothNumber() {
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

    public Date getDate() {
        return visit.getDate();
    }

}
