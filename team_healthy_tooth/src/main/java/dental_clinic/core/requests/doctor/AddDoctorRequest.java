package dental_clinic.core.requests.doctor;

import dental_clinic.core.domain.Doctor;

public class AddDoctorRequest {

    private Doctor doctor;

    public AddDoctorRequest(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
