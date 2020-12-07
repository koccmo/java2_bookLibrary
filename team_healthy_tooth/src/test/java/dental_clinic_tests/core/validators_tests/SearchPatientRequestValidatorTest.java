package dental_clinic_tests.core.validators_tests;

import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.services.validators.SearchPatientRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchPatientRequestValidatorTest {

    SearchPatientRequestValidator searchPatientRequestValidator = new SearchPatientRequestValidator();
    Ordering validOrdering = new Ordering("name", "ASC");
    Paging validPaging = new Paging(1, 1);

    @Test
    public void testEmptySearch(){
        CoreError expectedError = new CoreError("search", "Search request can't be empty");

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("", null, validOrdering, validPaging);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testFilledSurname(){

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("", "Surname", validOrdering, validPaging);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testFilledBoth(){

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Name", "Surname", validOrdering, validPaging);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testFilledName(){

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Name", null, validOrdering, validPaging);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testEnteredNameSurnameOrderBy(){
        CoreError expectedError = new CoreError("search", "Not valid input for ordering parameters");

        Ordering blankOrdering = new Ordering("name", "");
        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest("Name", "Surname", blankOrdering, validPaging);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertEquals(1, errors.size());
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testEnteredNameSurnameOrderDirection(){
        CoreError expectedError = new CoreError("search", "Not valid input for ordering parameters");
        Ordering blankOrdering = new Ordering("", "ASC");
        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest("Name", "Surname", blankOrdering, validPaging);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertEquals(1, errors.size());
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNoValidParametersForOrderBy(){
        CoreError expectedError = new CoreError("orderBy", "Not valid input for orderBy");
        Ordering invalidOrdering = new Ordering("not name and not surname, invalid", "DESC");
        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest("Name", "Surname", invalidOrdering, validPaging);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertEquals(1, errors.size());
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNoValidParametersForOrderDirection(){
        CoreError expectedError = new CoreError("orderDirection", "Not valid input for orderDirection");
        Ordering invalidOrdering = new Ordering("name", "not ASC and not DESC, invalid");
        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest("Name", "Surname", invalidOrdering, validPaging);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);
        assertTrue(errors.contains(expectedError));
        assertEquals(1, errors.size());
    }

    @Test
    public void testInvalidOrderByAndOrderDirection() {
        CoreError expectedError1 = new CoreError("orderBy", "Not valid input for orderBy");
        CoreError expectedError2 = new CoreError("orderDirection", "Not valid input for orderDirection");
        Ordering invalidOrdering = new Ordering("invalid orderBy", "invalid direction");
        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest("Name", "Surname", invalidOrdering, validPaging);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertEquals(2, errors.size());
        assertTrue(errors.contains(expectedError1));
        assertTrue(errors.contains(expectedError2));
    }

    @Test
    public void testNullSearchAndInvalidOrdering() {

        Ordering emptyOrdering = new Ordering("invalid", "invalid");
        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest(null, null, emptyOrdering, validPaging);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertEquals(3, errors.size());
    }
}