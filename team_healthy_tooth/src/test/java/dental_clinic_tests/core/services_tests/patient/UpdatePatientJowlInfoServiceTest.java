package dental_clinic_tests.core.services_tests.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.requests.patient.UpdatePatientsJowlInfoRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.UpdatePatientJowlInfoResponse;
import dental_clinic.core.services.patient.UpdatePatientJowlInfoService;
import dental_clinic.core.validators.patient.UpdatePatientJowlInfoRequestValidator;
import dental_clinic.core.database.patient.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
/*
@RunWith(MockitoJUnitRunner.class)
public class UpdatePatientJowlInfoServiceTest {

    @Mock
    private PatientRepository patientRepository;
    @Mock
    private UpdatePatientJowlInfoRequestValidator updatePatientJowlInfoRequestValidator;
    @InjectMocks
    private UpdatePatientJowlInfoService updatePatientJowlInfoService;

    @Test
    public void testValidationError() {
        Map<Integer, ToothStatus> jowlInfo = new HashMap<>();
        jowlInfo.put(1, ToothStatus.FASETE);
        CoreError error = new CoreError("id", "Not valid input of id");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(error);
        UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest = new UpdatePatientsJowlInfoRequest(1L, jowlInfo);

        Mockito.when(updatePatientJowlInfoRequestValidator.validate(updatePatientsJowlInfoRequest)).thenReturn(errorList);

        UpdatePatientJowlInfoResponse updatePatientJowlInfoResponse = updatePatientJowlInfoService.execute(updatePatientsJowlInfoRequest);

        assertTrue(updatePatientJowlInfoResponse.hasErrors());
        assertTrue(updatePatientJowlInfoResponse.getErrors().size() == 1);
    }

    @Test
    public void testDatabaseDoesNotContainId() {
        Map<Integer, ToothStatus> jowlInfo = new HashMap<>();
        jowlInfo.put(11, ToothStatus.FASETE);
        CoreError error = new CoreError("database", "Database doesn't contain patient with id 2");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(error);
        UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest = new UpdatePatientsJowlInfoRequest(1L, jowlInfo);

        Mockito.when(updatePatientJowlInfoRequestValidator.validate(updatePatientsJowlInfoRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.containsPatientWithSpecificId(1L)).thenReturn(false);

        UpdatePatientJowlInfoResponse updatePatientJowlInfoResponse = updatePatientJowlInfoService.execute(updatePatientsJowlInfoRequest);

        assertTrue(updatePatientJowlInfoResponse.hasErrors());
        assertTrue(updatePatientJowlInfoResponse.getErrors().size() == 1);
    }

    @Test
    public void testSuccessfulUpdate() {
        Map<Integer, ToothStatus> jowlInfo = new HashMap<>();
        jowlInfo.put(11, ToothStatus.FASETE);

        PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "01010114789");
        Patient patient = new Patient(personalData);
        patient.getPersonalData().setId(1L);
        List <Patient> patientList = new ArrayList<>();
        patientList.add(patient);

        UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest = new UpdatePatientsJowlInfoRequest(1L, jowlInfo);

        Mockito.when(updatePatientJowlInfoRequestValidator.validate(updatePatientsJowlInfoRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.containsPatientWithSpecificId(1L)).thenReturn(true);
        Mockito.when(patientRepository.getPatients()).thenReturn(patientList);

        UpdatePatientJowlInfoResponse updatePatientJowlInfoResponse = updatePatientJowlInfoService.execute(updatePatientsJowlInfoRequest);

        assertFalse(updatePatientJowlInfoResponse.hasErrors());
    }

}*/