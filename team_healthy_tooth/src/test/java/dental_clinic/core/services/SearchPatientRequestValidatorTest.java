package dental_clinic.core.services;

import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchPatientRequestValidatorTest {

    SearchPatientRequestValidator searchPatientRequestValidator = new SearchPatientRequestValidator();
    Ordering validOrdering = new Ordering("name", "ASC");

    @Test
    public void testEmptySearch(){
        CoreError expectedError = new CoreError("search", "Not valid input for search");

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("", null, validOrdering);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testFilledSurname(){

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("", "Surname", validOrdering);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testFilledBoth(){

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Name", "Surname", validOrdering);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testFilledName(){

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Name", null, validOrdering);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testEnteredNameSurnameOrderBy(){
        //expected error "search", "Not valid input for ordering parameters"

        Ordering blankOrdering = new Ordering("name", "");
        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest("Name", "Surname", blankOrdering);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertEquals(1, errors.size());
    }

    @Test
    public void testEnteredNameSurnameOrderDirection(){
        //expected error "search", "Not valid input for ordering parameters"

        Ordering blankOrdering = new Ordering("", "ASC");
        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest("Name", "Surname", blankOrdering);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertEquals(1, errors.size());
    }

    @Test
    public void testNoValidParametersForOrderBy(){
        //"orderBy", "Not valid input for orderBy"

        Ordering invalidOrdering = new Ordering("not name and not surname, invalid", "DESC");
        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest("Name", "Surname", invalidOrdering);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertEquals(1, errors.size());
    }

    @Test
    public void testNoValidParametersForOrderDirection(){
        //"orderDirection", "Not valid input for orderDirection"

        Ordering invalidOrdering = new Ordering("name", "not ASC and not DESC, invalid");
        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest("Name", "Surname", invalidOrdering);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertEquals(1, errors.size());
    }

    //И прочие комбинации на несколько ошибок

    @Test
    public void testInvalidOrderByAndOrderDirection() {

        Ordering invalidOrdering = new Ordering("invalid orderBy", "invalid direction");
        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest("Name", "Surname", invalidOrdering);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertEquals(2, errors.size());
    }

    @Test
    public void testNullSearchAndInvalidOrdering() {

        Ordering emptyOrdering = new Ordering("invalid", "invalid");
        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest(null, null, emptyOrdering);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertEquals(3, errors.size());
    }
}