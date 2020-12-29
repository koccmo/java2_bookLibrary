package dental_clinic.database;

import dental_clinic.core.domain.Doctor;

import java.util.List;

public interface DoctorDatabase {

    List<Doctor> getDoctorList ();

    void addDoctor(Doctor doctor);

    void deleteDoctorById(Long id);

    boolean containsDoctor(Doctor doctor);

    boolean containsId(Long id);

}
