package dental_clinic.core.domain;

public class DoctorAndGraphic {

    private Doctor doctor;
    private DoctorsWorkGraphic doctorsWorkGraphic;

    public DoctorAndGraphic(Doctor doctor, DoctorsWorkGraphic doctorsWorkGraphic) {
        this.doctor = doctor;
        this.doctorsWorkGraphic = doctorsWorkGraphic;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public DoctorsWorkGraphic getDoctorsWorkGraphic() {
        return doctorsWorkGraphic;
    }

    public void setDoctorsWorkGraphic(DoctorsWorkGraphic doctorsWorkGraphic) {
        this.doctorsWorkGraphic = doctorsWorkGraphic;
    }

    @Override
    public String toString() {
        return  "Doctor: " + doctor +
                "\nWorkGraphic: " + doctorsWorkGraphic + "\n";
    }
}
