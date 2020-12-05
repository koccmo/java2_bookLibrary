package dental_clinic_tests.core.services_tests;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.DeletePatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.DeletePatientResponse;
import dental_clinic.core.services.DeletePatientService;
import dental_clinic_tests.core.services_tests.matchers.IdMatcher;
import dental_clinic.core.services.validators.DeletePatientValidator;
import dental_clinic.database.PatientDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class DeletePatientServicesTest {

    @Mock private DeletePatientValidator deletePatientValidator;
    @Mock private PatientDatabase patientDatabase;
    @InjectMocks private DeletePatientService deletePatientService;

    @Test
    public void testNotValidIdInput(){
        DeletePatientRequest deletePatientRequest = new DeletePatientRequest(-8L);
        CoreError expectedError = new CoreError("id", "Not valid input for id");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);
        Mockito.when(deletePatientValidator.validate(deletePatientRequest)).thenReturn(errors);

        DeletePatientResponse deletePatientResponse = deletePatientService.execute(deletePatientRequest);

        assertTrue(deletePatientResponse.hasErrors());
        assertTrue(deletePatientResponse.getErrors().contains(expectedError));
        Mockito.verifyNoInteractions(patientDatabase);
    }

    @Test
    public void testNoIdInDatabase(){
        DeletePatientRequest deletePatientRequest = new DeletePatientRequest(8L);
        CoreError expectedError = new CoreError("id", "Database doesn't contain patient with id");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);
        Mockito.when(deletePatientValidator.validate(deletePatientRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientDatabase.containsPatientWithSpecificId(8L)).thenReturn(false);

        DeletePatientResponse deletePatientResponse = deletePatientService.execute(deletePatientRequest);

        assertTrue(deletePatientResponse.hasErrors());
        assertTrue(deletePatientResponse.getErrors().contains(expectedError));
    }

    @Test
    public void testAddedSuccessfully(){
        PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "12345678900");
        Patient patient = new Patient(personalData);
        patient.getPersonalData().setId(8L);
        List <Patient> patients = new ArrayList<>();
        patients.add(patient);
        DeletePatientRequest deletePatientRequest = new DeletePatientRequest(8L);
        Mockito.when(deletePatientValidator.validate(deletePatientRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientDatabase.containsPatientWithSpecificId(8L)).thenReturn(true);
        Mockito.when(patientDatabase.getPatients()).thenReturn(patients);

        DeletePatientResponse deletePatientResponse = deletePatientService.execute(deletePatientRequest);

        assertFalse(deletePatientResponse.hasErrors());
        Mockito.verify(patientDatabase).deletePatient(argThat(new IdMatcher(8L)));
    }
}