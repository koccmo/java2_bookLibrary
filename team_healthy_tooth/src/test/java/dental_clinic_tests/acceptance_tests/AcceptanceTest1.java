package dental_clinic_tests.acceptance_tests;

import dental_clinic.dependency_injection.ApplicationContext;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.AddPatientRequest;
import dental_clinic.core.requests.GetAllPatientsRequest;
import dental_clinic.core.responses.GetAllPatientsResponse;
import dental_clinic.core.services.AddPatientService;
import dental_clinic.core.services.GetAllPatientsService;
import dental_clinic.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest1 {

    private ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("dental_clinic");

    @Test
    public void test(){
        PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "25065612345");
        AddPatientRequest addPatientRequest = new AddPatientRequest(personalData);
        addPatientService().execute(addPatientRequest);
        addPatientService().execute(addPatientRequest);

        GetAllPatientsRequest getAllPatientsRequest = new GetAllPatientsRequest();
        GetAllPatientsResponse getAllPatientsResponse = getAllPatientsService().execute(getAllPatientsRequest);

        assertTrue(getAllPatientsResponse.getPatients().size() == 1);
    }

    private AddPatientService addPatientService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private GetAllPatientsService getAllPatientsService() {
        return applicationContext.getBean(GetAllPatientsService.class);
    }
}
