package dental_clinic_tests.core.validators_tests.visit;
/*
import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.Manipulation;
import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.domain.Visit;
import dental_clinic.core.requests.visit.AddVisitRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.visit.AddVisitValidator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class AddVisitValidatorTest {

    private AddVisitValidator addVisitValidator = new AddVisitValidator();
    private Optional<String> comment = Optional.empty();
    private Doctor doctor = new Doctor("Name Surname", "", "12345678");
    private List<Manipulation>manipulations = new ArrayList<>();
    private List<Long> manipulationIds = new ArrayList<>();

    @Test
    public void testNotValidInputId(){
        CoreError expectedError = new CoreError("id", "Not valid input of id");

        Visit newVisit = new Visit(1L, 11, comment, ToothStatus.FASETE, doctor, manipulations, new Date());
        AddVisitRequest addVisitRequest = new AddVisitRequest(-1L, newVisit, manipulationIds);

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);

        assertTrue(coreErrors.contains(expectedError));
    }

    @Test
    public void testNotValidInputToothNumber(){
        CoreError expectedError = new CoreError("tooth number", "Not valid input for tooth number");

        Visit newVisit = new Visit(1L, 2, comment, ToothStatus.FASETE, doctor, manipulations, new Date());
        AddVisitRequest addVisitRequest = new AddVisitRequest(1L, newVisit, manipulationIds);

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);

        assertTrue(coreErrors.contains(expectedError));
    }

    @Test
    public void testNotValidInputDoctor(){
        CoreError expectedError = new CoreError("doctor", "Doctor can't be empty");

        Visit visit = new Visit(1L, 11, comment, ToothStatus.FASETE, null, manipulations, new Date());
        AddVisitRequest addVisitRequest = new AddVisitRequest(1L, visit, manipulationIds);

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);
        coreErrors.forEach(System.out::println);
        assertTrue(coreErrors.contains(expectedError));
    }

    @Test
    public void testValidInput(){

        Visit visit = new Visit(1L, 11, comment, ToothStatus.FASETE, doctor, manipulations, new Date());
        AddVisitRequest addVisitRequest = new AddVisitRequest(1L, visit, manipulationIds);

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);

        coreErrors.forEach(System.out::println);
        assertTrue(coreErrors.isEmpty());
    }
}*/