package dental_clinic.database;

import dental_clinic.core.domain.Doctor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorDatabaseInMemory implements DoctorDatabase{

    private List<Doctor> doctors = addSomeDoctors();
    private Long id = 4L;

    @Override
    public List<Doctor> getDoctorList() {
        return doctors;
    }

    @Override
    public void addDoctor(Doctor doctor) {
        doctor.setId(id);
        id++;
        doctors.add(doctor);
    }

    @Override
    public void deleteDoctorById(Long id) {
        doctors.removeIf(doctor -> doctor.getId().equals(id));
    }

    @Override
    public boolean containsDoctor(Doctor doctor) {
        return doctors.stream()
            .anyMatch(doctor1 -> doctor1.equals(doctor));
    }

    @Override
    public boolean containsId(Long id) {
        return doctors.stream()
                .anyMatch(doctor -> doctor.getId().equals(id));
    }

    private List<Doctor> addSomeDoctors(){
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Doctor", "Zlo"));
        doctors.add(new Doctor("Doctor", "Haos"));
        doctors.add(new Doctor("Doc", "Incognito"));
        return doctors;
    }
}
