package dental_clinic.core.services;

import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.requests.AddVisitRequest;
import dental_clinic.core.responses.CoreError;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class AddVisitValidatorTest {

    AddVisitValidator addVisitValidator = new AddVisitValidator();
    Optional<String> comment = Optional.empty();

    @Test
    public void testNotValidInputId(){
        List<CoreError> expectedErrors = new ArrayList<>();
        expectedErrors.add(new CoreError("id", "Not valid input of id"));

        AddVisitRequest addVisitRequest = new AddVisitRequest(-1, 11, comment, ToothStatus.FASETE, "Zlo");

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);

        assertTrue(expectedErrors.equals(coreErrors));
    }

    @Test
    public void testNotValidInputToothNumber(){
        CoreError expectedError = new CoreError("tooth number", "Not valid input for tooth number");

        AddVisitRequest addVisitRequest = new AddVisitRequest(1, 2, comment, ToothStatus.FASETE, "Zlo");

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);

        coreErrors.forEach(System.out::println);
        assertTrue(coreErrors.contains(expectedError));
    }

    @Test
    public void testNotValidInputDoctor(){
        CoreError expectedError = new CoreError("doctor", "Not valid input for doctor");

        AddVisitRequest addVisitRequest = new AddVisitRequest(1, 11, comment, ToothStatus.FASETE, "");

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);

        assertTrue(coreErrors.contains(expectedError));
    }

    @Test
    public void testValidInput(){

        AddVisitRequest addVisitRequest = new AddVisitRequest(1, 11, comment, ToothStatus.FASETE, "Doctor");

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);

        coreErrors.forEach(System.out::println);
        assertTrue(coreErrors.isEmpty());
    }

}