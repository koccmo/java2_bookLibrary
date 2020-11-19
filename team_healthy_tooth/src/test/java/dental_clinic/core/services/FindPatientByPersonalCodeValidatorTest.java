package dental_clinic.core.services;

import dental_clinic.core.requests.FindPatientByPersonalCodeRequest;
import dental_clinic.core.responses.CoreError;
import junit.framework.TestCase;
import java.util.List;

import org.junit.Ignore;

@Ignore
public class FindPatientByPersonalCodeValidatorTest extends TestCase {

    FindPatientByPersonalCodeValidator validator;
    List<CoreError> testErrorList;

    public void testValidate() {
        this.validator = new FindPatientByPersonalCodeValidator();
        FindPatientByPersonalCodeValidatorTest test = new FindPatientByPersonalCodeValidatorTest();

        test.testGoodRequest1();
        test.testGoodRequest2();

        test.testBadRequest1();
        test.testBadRequest2();
    }

    private boolean isListEmpty(List<CoreError> testErrorList) {
        return testErrorList.isEmpty();
    }

    public void testGoodRequest1() {
        FindPatientByPersonalCodeRequest goodRequest =
                new FindPatientByPersonalCodeRequest("123456-12345");
        testErrorList = validator.validate(goodRequest);
        assertTrue(isListEmpty(testErrorList));
    }

    public void testGoodRequest2() {
        FindPatientByPersonalCodeRequest goodRequest =
                new FindPatientByPersonalCodeRequest("12345612345");
        testErrorList = validator.validate(goodRequest);
        assertTrue(isListEmpty(testErrorList));
    }

    public void testBadRequest1() {
        FindPatientByPersonalCodeRequest badRequest =
                new FindPatientByPersonalCodeRequest("x23456-12345");
        testErrorList = validator.validate(badRequest);
        assertFalse(isListEmpty(testErrorList));
    }

    public void testBadRequest2() {
        FindPatientByPersonalCodeRequest badRequest =
                new FindPatientByPersonalCodeRequest("123456-123456");
        testErrorList = validator.validate(badRequest);
        assertFalse(isListEmpty(testErrorList));
    }
}