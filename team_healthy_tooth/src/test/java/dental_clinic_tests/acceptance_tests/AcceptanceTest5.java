package dental_clinic_tests.acceptance_tests;

import dental_clinic.dependency_injection.ApplicationContext;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.AddPatientRequest;
import dental_clinic.core.requests.ChangePersonalDataRequest;
import dental_clinic.core.requests.GetAllPatientsRequest;
import dental_clinic.core.responses.GetAllPatientsResponse;
import dental_clinic.core.services.AddPatientService;
import dental_clinic.core.services.ChangePersonalDataService;
import dental_clinic.core.services.GetAllPatientsService;
import dental_clinic.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest5 {

    private ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("dental_clinic");

    @Test
    public void test(){
        PersonalData personalData1 = new PersonalData("Name", "Surname", "12345678", "25038910523");

        AddPatientRequest addPatientRequest1 = new AddPatientRequest(personalData1);
        addPatientService().execute(addPatientRequest1);

        ChangePersonalDataRequest changePersonalDataRequest = new ChangePersonalDataRequest(1L, "SurnameB", "");
        changePersonalDataService().execute(changePersonalDataRequest);

        ChangePersonalDataRequest changePersonalDataRequest2 = new ChangePersonalDataRequest(1L, "SurnameK", "87654321");
        changePersonalDataService().execute(changePersonalDataRequest2);

        GetAllPatientsRequest getAllPatientsRequest = new GetAllPatientsRequest();
        GetAllPatientsResponse getAllPatientsResponse = getAllPatientsService().execute(getAllPatientsRequest);

        assertTrue(getAllPatientsResponse.getPatients().size() == 1);

        assertTrue(getAllPatientsResponse.getPatients().get(0).getPersonalData().getSurname().equals("SurnameK"));
        assertTrue(getAllPatientsResponse.getPatients().get(0).getPersonalData().getPhone().equals("87654321"));

    }

    private AddPatientService addPatientService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private ChangePersonalDataService changePersonalDataService(){
        return applicationContext.getBean(ChangePersonalDataService.class);
    }

    private GetAllPatientsService getAllPatientsService() {
        return applicationContext.getBean(GetAllPatientsService.class);
    }

}
