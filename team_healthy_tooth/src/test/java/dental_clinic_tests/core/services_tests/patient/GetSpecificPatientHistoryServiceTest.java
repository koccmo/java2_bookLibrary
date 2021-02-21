package dental_clinic_tests.core.services_tests.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.patient.GetSpecificPatientHistoryRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.GetSpecificPatientHistoryResponse;
import dental_clinic.core.services.patient.GetSpecificPatientHistoryService;
import dental_clinic.core.validators.patient.GetSpecificPatientHistoryRequestValidator;
import dental_clinic.core.database.patient.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class GetSpecificPatientHistoryServiceTest {

    @Mock
    private PatientRepository patientRepository;
    @Mock
    private GetSpecificPatientHistoryRequestValidator getSpecificPatientHistoryRequestValidator;
    @InjectMocks
    private GetSpecificPatientHistoryService getSpecificPatientHistoryService;

    @Test
    public void testNotValidId(){
        CoreError expectedError = new CoreError("id", "Not valid input for id");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        GetSpecificPatientHistoryRequest getSpecificPatientHistoryRequest = new GetSpecificPatientHistoryRequest(-8L);

        Mockito.when(getSpecificPatientHistoryRequestValidator.validate(getSpecificPatientHistoryRequest)).thenReturn(errors);

        GetSpecificPatientHistoryResponse getSpecificPatientHistoryResponse = getSpecificPatientHistoryService.execute(getSpecificPatientHistoryRequest);

        assertTrue(getSpecificPatientHistoryResponse.hasErrors());
        assertTrue(getSpecificPatientHistoryResponse.getErrors().contains(expectedError));
        assertTrue(getSpecificPatientHistoryResponse.getErrors().size() == 1);
    }

    @Test
    public void testNoIdInDatabase(){
        CoreError expectedError = new CoreError("id", "Database doesn't contain patient with id");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        GetSpecificPatientHistoryRequest getSpecificPatientHistoryRequest = new GetSpecificPatientHistoryRequest(8L);

        Mockito.when(getSpecificPatientHistoryRequestValidator.validate(getSpecificPatientHistoryRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.containsPatientWithSpecificId(8L)).thenReturn(false);
        GetSpecificPatientHistoryResponse getSpecificPatientHistoryResponse = getSpecificPatientHistoryService.execute(getSpecificPatientHistoryRequest);

        assertTrue(getSpecificPatientHistoryResponse.hasErrors());
        assertTrue(getSpecificPatientHistoryResponse.getErrors().contains(expectedError));
        assertTrue(getSpecificPatientHistoryResponse.getErrors().size() == 1);
    }

    @Test
    public void testPatientFounded(){
        PersonalData personalData = new PersonalData("NAme", "Surname", "12345678", "25052512345");
        Patient patient = new Patient(personalData);
        List <Patient>patients = new ArrayList<>();
        patients.add(patient);
        patient.getPersonalData().setId(8L);
        Optional<Patient> specificPatient = Optional.of(patient);

        GetSpecificPatientHistoryRequest getSpecificPatientHistoryRequest = new GetSpecificPatientHistoryRequest(8L);

        Mockito.when(getSpecificPatientHistoryRequestValidator.validate(getSpecificPatientHistoryRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.containsPatientWithSpecificId(8L)).thenReturn(true);
        Mockito.when(patientRepository.getPatients()).thenReturn(patients);

        GetSpecificPatientHistoryResponse getSpecificPatientHistoryResponse = getSpecificPatientHistoryService.execute(getSpecificPatientHistoryRequest);

        assertFalse(getSpecificPatientHistoryResponse.hasErrors());
        assertTrue(getSpecificPatientHistoryResponse.getSpecificPatient().equals(specificPatient));
    }
}