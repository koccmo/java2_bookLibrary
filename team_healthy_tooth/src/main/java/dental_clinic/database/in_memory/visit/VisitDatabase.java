package dental_clinic.database.in_memory.visit;

import dental_clinic.core.domain.Visit;

import java.util.List;

public interface VisitDatabase {

    void addVisit (Visit visit);

    List<Visit> searchVisitByPatientId(Long patientsId);

    List<Visit> searchVisitByDate(int dayFrom, int dayTo, int monthFrom, int monthTo);
}
