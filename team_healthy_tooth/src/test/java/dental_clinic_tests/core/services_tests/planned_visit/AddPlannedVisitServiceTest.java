package dental_clinic_tests.core.services_tests.planned_visit;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.requests.plannedVisit.AddPlannedVisitRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.AddPlannedVisitResponse;
import dental_clinic.core.services.patient.AddPatientService;
import dental_clinic.core.services.planned_visit.AddPlannedVisitService;
import dental_clinic.core.validators.planned_visit.AddPlannedVisitRequestValidator;
import dental_clinic.database.in_memory.doctor.DoctorDatabase;
import dental_clinic.database.in_memory.patient.PatientDatabase;
import dental_clinic.database.in_memory.planned_visit.PlannedVisitsInMemoryDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AddPlannedVisitServiceTest {

    @Mock
    private AddPlannedVisitRequestValidator addPlannedVisitRequestValidator;
    @Mock
    private PlannedVisitsInMemoryDatabase plannedVisitsInMemoryDatabase;
    @Mock
    private PatientDatabase patientDatabase;
    @Mock
    private AddPatientService addPatientService;
    @Mock
    private DoctorDatabase doctorDatabase;
    @InjectMocks
    private AddPlannedVisitService addPlannedVisitService;

    PersonalData validPersonalData = new PersonalData("Name", "Surname", "12345678", "01012547896");
    String validTime = "03-05-2021 15:30";
    Doctor doctor = new Doctor("Dokiii", "Ukol");


    @Test
    public void testTimeValidationErrors() {
        PersonalData notValidPersonalData = new PersonalData("", "Surname", "12345678", "01012547896");
        AddPlannedVisitRequest addPlannedVisitRequest = new AddPlannedVisitRequest(true, validTime, notValidPersonalData, 1L);

        List<CoreError> errorList = new ArrayList<>();
        CoreError coreError = new CoreError("Personal data : name", "Name can't be empty");
        errorList.add(coreError);
        Mockito.when(addPlannedVisitRequestValidator.validate(addPlannedVisitRequest)).thenReturn(errorList);


        AddPlannedVisitResponse addPlannedVisitResponse = addPlannedVisitService.execute(addPlannedVisitRequest);

        assertTrue(addPlannedVisitResponse.hasErrors());
        assertTrue(addPlannedVisitResponse.getErrors().size() == 1);
        assertTrue(addPlannedVisitResponse.getErrors().contains(coreError));
        Mockito.verifyNoInteractions(plannedVisitsInMemoryDatabase);
    }

    @Test
    public void testVisitPlannedInThePast() {
        AddPlannedVisitRequest addPlannedVisitRequest = new AddPlannedVisitRequest(true, "03-05-2020 15:30", validPersonalData, 1L);

        List<CoreError> errorList = new ArrayList<>();
        CoreError coreError = new CoreError("date", "Visit date must be in future");
        errorList.add(coreError);
        Mockito.when(addPlannedVisitRequestValidator.validate(addPlannedVisitRequest)).thenReturn(new ArrayList<>());

        AddPlannedVisitResponse addPlannedVisitResponse = addPlannedVisitService.execute(addPlannedVisitRequest);

        assertTrue(addPlannedVisitResponse.hasErrors());
        assertTrue(addPlannedVisitResponse.getErrors().size() == 1);
        assertTrue(addPlannedVisitResponse.getErrors().contains(coreError));
        Mockito.verifyNoInteractions(plannedVisitsInMemoryDatabase);
    }
}