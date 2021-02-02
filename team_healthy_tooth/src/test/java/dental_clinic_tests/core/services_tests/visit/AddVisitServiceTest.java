package dental_clinic_tests.core.services_tests.visit;

import dental_clinic.core.domain.*;
import dental_clinic.core.requests.visit.AddVisitRequest;
import dental_clinic.core.responses.visit.AddVisitResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.services.visit.AddVisitService;
import dental_clinic.core.validators.visit.AddVisitValidator;
import dental_clinic.core.database.doctor.DoctorRepository;
import dental_clinic.core.database.patient.PatientRepository;
import dental_clinic.core.database.visit.VisitRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
/*
@RunWith(MockitoJUnitRunner.class)
public class AddVisitServiceTest {

    @Mock
    private AddVisitValidator addVisitValidator;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private VisitRepository visitRepository;
    @InjectMocks
    AddVisitService addVisitService;

    Doctor doctor = new Doctor("Name Surname", "Surname", "12345678");
    List <Manipulation> manipulationList = new ArrayList<>();
    List<Long> manipulationIds = new ArrayList<>();

    @Test
    public void testNotCorrectToothNumber() {
        Visit visit = new Visit(1L, 2, Optional.empty(), ToothStatus.FASETE, doctor, manipulationList, new Date());
        AddVisitRequest addVisitRequest = new AddVisitRequest(1L, visit, manipulationIds);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("tooth number", "Not valid input for tooth number");
        errors.add(expectedError);
        Mockito.when(addVisitValidator.validate(addVisitRequest)).thenReturn(errors);

        AddVisitResponse addVisitResponse = addVisitService.execute(addVisitRequest);
        assertTrue(addVisitResponse.hasErrors());
        assertTrue(addVisitResponse.getErrors().size() == 1);
        assertTrue(errors.contains(expectedError));
        Mockito.verifyNoInteractions(patientRepository);
        Mockito.verifyNoInteractions(doctorRepository);
    }

    @Test
    public void testEmptyRequest() {
        Visit visit = new Visit(1L, null, null, null, null, manipulationList, new Date());
        AddVisitRequest addVisitRequest = new AddVisitRequest(null, visit, manipulationIds);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError1 = new CoreError("id", "Not valid input of id");
        CoreError expectedError2 = new CoreError("tooth number", "Not valid input for tooth number");
        CoreError expectedError3 = new CoreError("doctor", "Not valid input for doctor");
        errors.add(expectedError1);
        errors.add(expectedError2);
        errors.add(expectedError3);
        Mockito.when(addVisitValidator.validate(addVisitRequest)).thenReturn(errors);

        AddVisitResponse addVisitResponse = addVisitService.execute(addVisitRequest);
        assertTrue(addVisitResponse.hasErrors());
        assertTrue(addVisitResponse.getErrors().size() == 3);
        assertTrue(errors.contains(expectedError1));
        assertTrue(errors.contains(expectedError2));
        assertTrue(errors.contains(expectedError3));
        Mockito.verifyNoInteractions(patientRepository);
        Mockito.verifyNoInteractions(doctorRepository);
    }

    @Test
    public void testDatabaseDoesNotContainRequestedId() {
        Visit visit = new Visit(1L, 11, Optional.empty(), ToothStatus.FASETE, doctor, manipulationList, new Date());
        AddVisitRequest addVisitRequest = new AddVisitRequest(5L, visit, manipulationIds);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("id", "Database doesnt't contain patient with id 5");
        errors.add(expectedError);
        Mockito.when(addVisitValidator.validate(addVisitRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.containsPatientWithSpecificId(5L)).thenReturn(false);


        AddVisitResponse addVisitResponse = addVisitService.execute(addVisitRequest);
        assertTrue(addVisitResponse.hasErrors());
        assertTrue(addVisitResponse.getErrors().size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidInputForDoctor() {
        Visit visit = new Visit(1L, 11, Optional.empty(), ToothStatus.FASETE, doctor, manipulationList, new Date());
        AddVisitRequest addVisitRequest = new AddVisitRequest(5L, visit, manipulationIds);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("doctor", "Database doesn't contains specific doctor");
        errors.add(expectedError);
        Mockito.when(addVisitValidator.validate(addVisitRequest)).thenReturn(new ArrayList<>());

        AddVisitResponse addVisitResponse = addVisitService.execute(addVisitRequest);
        assertTrue(addVisitResponse.hasErrors());
        assertTrue(addVisitResponse.getErrors().size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testVisitSuccessfullyAdded() {
        PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "25052512345");
        Patient patient = new Patient(personalData);
        List<Patient> patients = new ArrayList<>();
        patient.getPersonalData().setId(1L);
        patients.add(patient);
        Visit visit = new Visit(1L, 11, Optional.empty(), ToothStatus.FASETE, doctor, manipulationList, new Date());
        AddVisitRequest addVisitRequest = new AddVisitRequest(1L, visit, manipulationIds);
        Mockito.when(addVisitValidator.validate(addVisitRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.containsPatientWithSpecificId(1L)).thenReturn(true);
        Mockito.when(patientRepository.getPatients()).thenReturn(patients);

        AddVisitResponse addVisitResponse = addVisitService.execute(addVisitRequest);
        assertFalse(addVisitResponse.hasErrors());
    }
}*/