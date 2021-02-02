package dental_clinic.core.database.visit;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.Visit;

import java.util.List;

public interface VisitRepository {

    void addVisit (Visit visit);

    List<Visit> searchVisitByPatientId(Long patientsId);

    List<Visit> searchVisitByDate(int dayFrom, int dayTo, int monthFrom, int monthTo);
}
