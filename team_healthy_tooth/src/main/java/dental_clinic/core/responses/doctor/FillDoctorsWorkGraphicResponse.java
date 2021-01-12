package dental_clinic.core.responses.doctor;

import dental_clinic.core.domain.WorkGraphic;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class FillDoctorsWorkGraphicResponse extends CoreResponse {

    private Long id;
    private WorkGraphic workGraphic;

    public FillDoctorsWorkGraphicResponse(Long id, WorkGraphic workGraphic) {
        this.id = id;
        this.workGraphic = workGraphic;
    }

    public FillDoctorsWorkGraphicResponse(List<CoreError> errors) {
        super(errors);
    }

    public Long getId() {
        return id;
    }

    public WorkGraphic getWorkGraphic() {
        return workGraphic;
    }
}
