package dental_clinic_tests.core.validators_tests.visit;

import dental_clinic.core.domain.Doctor;
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

    AddVisitValidator addVisitValidator = new AddVisitValidator();
    Optional<String> comment = Optional.empty();
    Doctor doctor = new Doctor("Name Surname", "");

    @Test
    public void testNotValidInputId(){
        CoreError expectedError = new CoreError("id", "Not valid input of id");

        Visit newVisit = new Visit( 11, comment, ToothStatus.FASETE, doctor, new Date());
        AddVisitRequest addVisitRequest = new AddVisitRequest(-1L, newVisit);

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);

        assertTrue(coreErrors.contains(expectedError));
    }

    @Test
    public void testNotValidInputToothNumber(){
        CoreError expectedError = new CoreError("tooth number", "Not valid input for tooth number");

        Visit newVisit = new Visit(2, comment, ToothStatus.FASETE, doctor, new Date());
        AddVisitRequest addVisitRequest = new AddVisitRequest(1L, newVisit);

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);

        assertTrue(coreErrors.contains(expectedError));
    }

    @Test
    public void testNotValidInputDoctor(){
        CoreError expectedError = new CoreError("doctor", "Doctor can't be empty");

        Visit visit = new Visit(11, comment, ToothStatus.FASETE, null, new Date());
        AddVisitRequest addVisitRequest = new AddVisitRequest(1L, visit);

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);
        coreErrors.forEach(System.out::println);
        assertTrue(coreErrors.contains(expectedError));
    }

    @Test
    public void testValidInput(){

        Visit visit = new Visit(11, comment, ToothStatus.FASETE, doctor, new Date());
        AddVisitRequest addVisitRequest = new AddVisitRequest(1L, visit);

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);

        coreErrors.forEach(System.out::println);
        assertTrue(coreErrors.isEmpty());
    }

}