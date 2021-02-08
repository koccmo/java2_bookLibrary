package dental_clinic_tests.core.services_tests.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.patient.GetAllPatientsRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.GetAllPatientsResponse;
import dental_clinic.core.services.patient.GetAllPatientsService;
import dental_clinic.core.validators.patient.GetAllPatientsRequestValidator;
import dental_clinic.core.database.patient.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetAllPatientsServiceTest {

    @Mock
    private PatientRepository patientRepository;
    @Mock
    private GetAllPatientsRequestValidator getAllPatientsRequestValidator;
    @InjectMocks
    private GetAllPatientsService getAllPatientsService;

    @Test
    public void testNoPatientsInDatabase(){
        CoreError expectedError = new CoreError("database", "Database is empty");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        GetAllPatientsRequest getAllPatientsRequest = new GetAllPatientsRequest();
        Mockito.when(getAllPatientsRequestValidator.validate(getAllPatientsRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.getPatients()).thenReturn(new ArrayList<>());

        GetAllPatientsResponse getAllPatientsResponse = getAllPatientsService.execute(getAllPatientsRequest);

        assertTrue(getAllPatientsResponse.hasErrors());
        assertTrue(getAllPatientsResponse.getErrors().contains(expectedError));
        assertTrue(getAllPatientsResponse.getErrors().size() == 1);
    }

    @Test
    public void testReturnsListOfPatients(){
        PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "25052512345");
        Patient patient = new Patient(personalData);
        List<Patient>patients = new ArrayList<>();
        patients.add(patient);

        GetAllPatientsRequest getAllPatientsRequest = new GetAllPatientsRequest();
        Mockito.when(getAllPatientsRequestValidator.validate(getAllPatientsRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.getPatients()).thenReturn(patients);

        GetAllPatientsResponse getAllPatientsResponse = getAllPatientsService.execute(getAllPatientsRequest);

        assertFalse(getAllPatientsResponse.hasErrors());
        assertTrue(getAllPatientsResponse.getPatients().equals(patients));
    }

}