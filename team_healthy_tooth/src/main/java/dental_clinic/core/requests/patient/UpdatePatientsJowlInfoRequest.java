package dental_clinic.core.requests.patient;

import dental_clinic.core.domain.ToothStatus;

import java.util.Map;

public class UpdatePatientsJowlInfoRequest {

    private Long id;
    private Map<Integer, ToothStatus> jowlInfo;

    public UpdatePatientsJowlInfoRequest(Long id, Map<Integer, ToothStatus> jowlInfo) {
        this.id = id;
        this.jowlInfo = jowlInfo;
    }

    public Long getId() {
        return id;
    }

    public Map<Integer, ToothStatus> getJowlInfo() {
        return jowlInfo;
    }
}
