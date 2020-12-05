package dental_clinic_tests.acceptance_tests;

import dental_clinic.dependency_injection.ApplicationContext;
import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.AddPatientRequest;
import dental_clinic.core.requests.DeletePatientRequest;
import dental_clinic.core.requests.GetAllPatientsRequest;
import dental_clinic.core.requests.SearchPatientByPersonalCodeRequest;
import dental_clinic.core.responses.GetAllPatientsResponse;
import dental_clinic.core.responses.SearchPatientByPersonalCodeResponse;
import dental_clinic.core.services.AddPatientService;
import dental_clinic.core.services.DeletePatientService;
import dental_clinic.core.services.GetAllPatientsService;
import dental_clinic.core.services.SearchPatientsByPersonalCodeService;
import dental_clinic.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest3 {

    private ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("dental_clinic");

    @Test
    public void test(){
        PersonalData personalData1 = new PersonalData("Name", "Surname", "12345678", "25038910523");
        Patient patient = new Patient(personalData1);
        PersonalData personalData2 = new PersonalData("NameA", "SurnameB", "12345675", "12345668911");
        AddPatientRequest addPatientRequest1 = new AddPatientRequest(personalData1);
        AddPatientRequest addPatientRequest2 = new AddPatientRequest(personalData2);
        addPatientService().execute(addPatientRequest1);
        addPatientService().execute(addPatientRequest2);

        DeletePatientRequest deletePatientRequest = new DeletePatientRequest(2L);
        deletePatientService().execute(deletePatientRequest);

        GetAllPatientsRequest getAllPatientsRequest = new GetAllPatientsRequest();
        GetAllPatientsResponse getAllPatientsResponse = getAllPatientsService().execute(getAllPatientsRequest);

        SearchPatientByPersonalCodeRequest searchPatientByPersonalCodeRequest = new SearchPatientByPersonalCodeRequest("25038910523");
        SearchPatientByPersonalCodeResponse searchPatientByPersonalCodeResponse = searchPatientsByPersonalCodeService().execute(searchPatientByPersonalCodeRequest);

        assertTrue(getAllPatientsResponse.getPatients().size() == 1);
        assertTrue(searchPatientByPersonalCodeResponse.getFoundPatient().equals(patient));

    }

    private AddPatientService addPatientService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private DeletePatientService deletePatientService(){
        return applicationContext.getBean(DeletePatientService.class);
    }

    private GetAllPatientsService getAllPatientsService() {
        return applicationContext.getBean(GetAllPatientsService.class);
    }

    private SearchPatientsByPersonalCodeService searchPatientsByPersonalCodeService(){
        return applicationContext.getBean(SearchPatientsByPersonalCodeService.class);
    }

}
