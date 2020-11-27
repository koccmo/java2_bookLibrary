package dental_clinic.core.services;

import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchPatientServiceTest {

    SearchPatientRequestValidator searchPatientRequestValidator = new SearchPatientRequestValidator();
    Ordering validOrdering = new Ordering("name", "ASC");
    Paging validPaging = new Paging(1, 1);

    @Test
    public void testEmptyRequest(){
        CoreError expectedError = new CoreError("search", "Not valid input for search");

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest(null, "", validOrdering, validPaging);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidInputForOrdering(){
        CoreError expectedError = new CoreError("search", "Not valid input for ordering parameters");

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("name", "ASC", new Ordering("name", null), validPaging);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidInputForOrderBy(){
        CoreError expectedError = new CoreError("orderBy", "Not valid input for orderBy");

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("name", "Surname", new Ordering("Bob", "ASC"), validPaging);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidInputForOrderDirection(){
        CoreError expectedError = new CoreError("orderDirection", "Not valid input for orderDirection");

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("name", "Surname", new Ordering("name", "123"), validPaging);
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidInputForPageNumber(){
        CoreError expectedError = new CoreError("pageNumber", "Not valid input for page number");

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("name", "Surname", validOrdering, new Paging(-8, 1));
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidInputForPageSize(){
        CoreError expectedError = new CoreError("pageSize", "Not valid input for page size");

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("name", "Surname", validOrdering, new Paging(1, -7));
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidInputForPaging(){
        CoreError expectedError = new CoreError("search", "Not valid input for paging parameters");

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("name", "Surname", validOrdering, new Paging(1, null));
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidInputForPagingAndOrdering(){
        CoreError expectedError1 = new CoreError("search", "Not valid input for ordering parameters");
        CoreError expectedError2 = new CoreError("search", "Not valid input for paging parameters");

        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest("name", "Surname", new Ordering("name", null), new Paging(1, null));
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errors.size() == 2);
        assertTrue(errors.contains(expectedError1));
        assertTrue(errors.contains(expectedError2));
    }

}