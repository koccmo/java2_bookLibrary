package dental_clinic.core.requests.visit;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.domain.Visit;

import java.util.Date;
import java.util.Optional;

public class AddVisitRequest {

    private Long patientsId;
    private Visit visit;


    public AddVisitRequest(Long patientsId, Visit visit){
        this.patientsId = patientsId;
        this.visit = visit;
    }

    public Long getPatientsId() {
        return patientsId;
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

    public Doctor getDoctor() {
        return visit.getDoctor();
    }

    public Date getDate() {
        return visit.getDate();
    }

}
