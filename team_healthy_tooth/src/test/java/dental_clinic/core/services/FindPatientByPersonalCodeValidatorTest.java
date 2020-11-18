package dental_clinic.core.services;

import dental_clinic.core.requests.FindPatientByPersonalCodeRequest;
import dental_clinic.core.responses.CoreError;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class FindPatientByPersonalCodeValidatorTest extends TestCase {

    public void testValidate() {
        FindPatientByPersonalCodeValidator validator = new FindPatientByPersonalCodeValidator();
        List<CoreError> testErrorList;

        List<CoreError> emptyErrorList = new ArrayList<>(); //for good requests

        FindPatientByPersonalCodeRequest requestGood1 =
                new FindPatientByPersonalCodeRequest("123456-12345");
        testErrorList = validator.validate(requestGood1);
        //assertEquals(testErrorList, emptyErrorList);

        testErrorList.clear();

        FindPatientByPersonalCodeRequest requestGood2 =
                new FindPatientByPersonalCodeRequest("12345612345");
        testErrorList = validator.validate(requestGood2);
        //assertEquals(testErrorList, emptyErrorList);

        testErrorList.clear();

        List<CoreError> personalCodeErrorInList = new ArrayList<>(); //for bad requests
        personalCodeErrorInList.add(new CoreError("Personal data : personal code",
                        "Invalid input for personal code!"));

        FindPatientByPersonalCodeRequest requestBad1 =
                new FindPatientByPersonalCodeRequest("personalnij kod");
        testErrorList = validator.validate(requestBad1);
        assertEquals(testErrorList, personalCodeErrorInList);

        testErrorList.clear();

        FindPatientByPersonalCodeRequest requestBad2 =
                new FindPatientByPersonalCodeRequest("123456-123456");
        testErrorList = validator.validate(requestBad2);
        assertEquals(testErrorList, personalCodeErrorInList);

    }
}