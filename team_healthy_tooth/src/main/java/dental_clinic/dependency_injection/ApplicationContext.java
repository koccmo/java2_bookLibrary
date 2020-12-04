package dental_clinic.dependency_injection;

import dental_clinic.core.services.*;
import dental_clinic.database.PatientDatabase;
import dental_clinic.database.PatientDatabaseImpl;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(PatientDatabase.class, new PatientDatabaseImpl());

        beans.put(AddPatientRequestValidator.class, new AddPatientRequestValidator());
        beans.put(AddPatientService.class,
                new AddPatientService(getBean(PatientDatabase.class), getBean(AddPatientRequestValidator.class)));

        beans.put(DeletePatientValidator.class, new DeletePatientValidator());
        beans.put(DeletePatientService.class,
                new DeletePatientService(getBean(PatientDatabase.class), getBean(DeletePatientValidator.class)));

        beans.put(GetAllPatientsRequestValidator.class, new GetAllPatientsRequestValidator());
        beans.put(GetAllPatientsService.class,
                new GetAllPatientsService(getBean(PatientDatabase.class), getBean(GetAllPatientsRequestValidator.class)));

        beans.put(GetSpecificPatientHistoryRequestValidator.class, new GetSpecificPatientHistoryRequestValidator());
        beans.put(GetSpecificPatientHistoryService.class,
                new GetSpecificPatientHistoryService(getBean(PatientDatabase.class), getBean(GetSpecificPatientHistoryRequestValidator.class)));

        beans.put(SearchPatientByPersonalCodeRequestValidator.class, new SearchPatientByPersonalCodeRequestValidator());
        beans.put(SearchPatientsByPersonalCodeService.class,
                new SearchPatientsByPersonalCodeService(getBean(PatientDatabase.class), getBean(SearchPatientByPersonalCodeRequestValidator.class)));

        beans.put(AddVisitValidator.class, new AddVisitValidator());
        beans.put(AddVisitService.class,
                new AddVisitService(getBean(PatientDatabase.class), getBean(AddVisitValidator.class)));

        beans.put(ContainsDatabaseIdValidator.class, new ContainsDatabaseIdValidator());
        beans.put(ContainsDatabaseIdService.class,
                new ContainsDatabaseIdService(getBean(PatientDatabase.class), getBean(ContainsDatabaseIdValidator.class)));

        beans.put(SearchPatientRequestValidator.class, new SearchPatientRequestValidator());
        beans.put(SearchPatientService.class,
                new SearchPatientService(getBean(PatientDatabase.class), getBean(SearchPatientRequestValidator.class)));

        beans.put(GetPatientCardRequestValidator.class, new GetPatientCardRequestValidator());
        beans.put(GetPatientCardService.class,
                new GetPatientCardService(getBean(PatientDatabase.class), getBean(GetPatientCardRequestValidator.class)));

        beans.put(ChangePersonalDataValidator.class, new ChangePersonalDataValidator());
        beans.put(ChangePersonalDataService.class,
                new ChangePersonalDataService(getBean(PatientDatabase.class), getBean(ChangePersonalDataValidator.class)));
    }


    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }

}
