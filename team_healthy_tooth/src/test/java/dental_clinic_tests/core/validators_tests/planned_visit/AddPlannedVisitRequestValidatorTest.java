package dental_clinic_tests.core.validators_tests.planned_visit;
/*
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.plannedVisit.AddPlannedVisitRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.planned_visit.AddPlannedVisitRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddPlannedVisitRequestValidatorTest {

    AddPlannedVisitRequestValidator addPlannedVisitRequestValidator = new AddPlannedVisitRequestValidator();
    PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "25012147896");

    @Test
    public void testNotValidDateFormat() {
        CoreError expectedError = new CoreError("date", "Not valid input for date");

        AddPlannedVisitRequest addPlannedVisitRequest = new AddPlannedVisitRequest(true, "25 01 12 15:20", personalData, 1L);
        List <CoreError> actualErrors = addPlannedVisitRequestValidator.validate(addPlannedVisitRequest);

        assertTrue(actualErrors.size() == 1);
        assertTrue(actualErrors.contains(expectedError));
    }

    @Test
    public void testNotValidDate() {
        CoreError expectedError = new CoreError("date", "Not valid input for date");

        AddPlannedVisitRequest addPlannedVisitRequest = new AddPlannedVisitRequest(true, "30-02-2021 15:20", personalData, 1L);
        List <CoreError> actualErrors = addPlannedVisitRequestValidator.validate(addPlannedVisitRequest);

        assertTrue(actualErrors.size() == 1);
        assertTrue(actualErrors.contains(expectedError));
    }

    @Test
    public void testEmptyName(){
        PersonalData personalData1 = new PersonalData(null, "Surname", "12345678", "20012014789");
        AddPlannedVisitRequest addPlannedVisitRequest = new AddPlannedVisitRequest(true, "25-02-2025 15:30", personalData1, 1L);
        CoreError expectedError = new CoreError("Personal data : name", "Name can't be empty");
        List<CoreError> errorList = addPlannedVisitRequestValidator.validate(addPlannedVisitRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testEmptySurname(){
        PersonalData personalData1 = new PersonalData("Name", "", "12345678", "20012014789");
        AddPlannedVisitRequest addPlannedVisitRequest = new AddPlannedVisitRequest(true, "25-02-2025 15:30", personalData1, 1L);
        CoreError expectedError = new CoreError("Personal data : surname", "Surname can't be empty");
        List<CoreError> errorList = addPlannedVisitRequestValidator.validate(addPlannedVisitRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testEmptySurnameAndName(){
        PersonalData personalData1 = new PersonalData(null, "", "12345678", "20012014789");
        AddPlannedVisitRequest addPlannedVisitRequest = new AddPlannedVisitRequest(true, "25-02-2025 15:30", personalData1, 2L);
        CoreError expectedError1 = new CoreError("Personal data : name", "Name can't be empty");
        CoreError expectedError2 = new CoreError("Personal data : surname", "Surname can't be empty");
        List<CoreError> errorList = addPlannedVisitRequestValidator.validate(addPlannedVisitRequest);

        assertTrue(errorList.size() == 2);
        assertTrue(errorList.contains(expectedError1));
        assertTrue(errorList.contains(expectedError2));
    }

    @Test
    public void testDoctorsIdValidationError(){
        PersonalData personalData1 = new PersonalData("Bob", "Schmit", "12345678", "20012014789");
        AddPlannedVisitRequest addPlannedVisitRequest = new AddPlannedVisitRequest(true, "25-02-2025 15:30", personalData1, -2L);
        CoreError expectedError1 = new CoreError("id", "Not valid input for id");
        List<CoreError> errorList = addPlannedVisitRequestValidator.validate(addPlannedVisitRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError1));
    }

    @Test
    public void testNoErrors(){
        PersonalData personalData1 = new PersonalData("Name", "Surname", "12345678", "20012014789");
        AddPlannedVisitRequest addPlannedVisitRequest = new AddPlannedVisitRequest(true, "25-02-2025 15:30", personalData1, 2L);

        List<CoreError> errorList = addPlannedVisitRequestValidator.validate(addPlannedVisitRequest);

        assertTrue(errorList.isEmpty());
    }

}*/