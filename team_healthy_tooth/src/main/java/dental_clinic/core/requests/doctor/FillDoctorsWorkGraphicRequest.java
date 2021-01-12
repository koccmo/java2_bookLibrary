package dental_clinic.core.requests.doctor;

import dental_clinic.core.domain.WorkGraphic;

import java.util.GregorianCalendar;

public class FillDoctorsWorkGraphicRequest {

    private Long id;
    private WorkGraphic workGraphic;

    public FillDoctorsWorkGraphicRequest(Long id, WorkGraphic workGraphic) {
        this.id = id;
        this.workGraphic = workGraphic;
    }

    public Long getId() {
        return id;
    }

    public WorkGraphic getWorkGraphic() {
        return workGraphic;
    }
}
