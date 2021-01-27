package dental_clinic.core.database.doctor;

import dental_clinic.core.domain.Doctor;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

public interface DoctorDatabase {

    List<Doctor> getDoctorList ();

    void addDoctor(Doctor doctor);

    boolean deleteDoctorById(Long id);

    boolean containsDoctor(Doctor doctor);

    boolean containsId(Long id);

    boolean specificDoctorIsEmployed(Doctor doctor);

    Optional<Doctor> getDoctorById(Long id);

    void updateWorkGraphicForSpecificDate (Long id, Integer day, String timeFrom, String timeTo);

}
