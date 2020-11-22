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
    }

    @Test
    public void testEnteredNameSurnameOrderDirection(){
        //expected error "search", "Not valid input for ordering parameters"
    }

    @Test
    public void testNoValidParametersForOrderBy(){
        //"orderBy", "Not valid input for orderBy"
    }

    @Test
    public void testNoValidParametersForOrderDirection(){
        //"orderDirection", "Not valid input for orderDirection"
    }

    //И прочие комбинации на несколько ошибок

}