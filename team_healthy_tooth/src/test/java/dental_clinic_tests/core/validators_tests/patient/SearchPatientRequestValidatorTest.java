package dental_clinic_tests.core.validators_tests.patient;

import dental_clinic.core.domain.OrderingDirection;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.patient.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.patient.SearchPatientRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchPatientRequestValidatorTest {

    SearchPatientRequestValidator searchPatientRequestValidator = new SearchPatientRequestValidator();
    Ordering validOrdering = new Ordering("name", OrderingDirection.ASC);
    Paging validPaging = new Paging(1, 10);

    @Test
    public void testEmptySearchRequest() {
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest(null, validOrdering, validPaging);
        CoreError expectedError = new CoreError("search", "Search request can't be empty");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testNotValidInput() {
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("k34", validOrdering, validPaging);
        CoreError expectedError = new CoreError("search", "Search by surname can contain only letters");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testNotValidInputForPersonalCode() {
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("45434", validOrdering, validPaging);
        CoreError expectedError = new CoreError("personal code", "Not valid input for personal code");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testNotValidInputForOrderingDirection1() {
        Ordering ordering = new Ordering("name", OrderingDirection.NULL);
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", ordering, validPaging);
        CoreError expectedError = new CoreError("search", "Not valid input for ordering parameters");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testNotValidInputForOrderingDirection2() {
        Ordering ordering = new Ordering("name", OrderingDirection.NOT_VALID);
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", ordering, validPaging);
        CoreError expectedError = new CoreError("orderDirection", "Not valid input for orderDirection");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testNotValidInputForOrderBy() {
        Ordering ordering = new Ordering("tooth", OrderingDirection.ASC);
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", ordering, validPaging);
        CoreError expectedError = new CoreError("orderBy", "Not valid input for orderBy");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testOrderingBothNull() {
        Ordering ordering = new Ordering(null, OrderingDirection.NULL);
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", ordering, validPaging);

        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.isEmpty());
    }

    @Test
    public void testNotValidInputForPagingOneFilled() {
        Paging paging = new Paging(1, null);
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", validOrdering, paging);
        CoreError expectedError = new CoreError("search", "Not valid input for paging parameters");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testPagingNull() {
        Paging paging = new Paging(null, null);
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", validOrdering, paging);
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.isEmpty());
    }

    @Test
    public void testNotValidInputForPageNumber() {
        Paging paging = new Paging(-1, 10);
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", validOrdering, paging);
        CoreError expectedError = new CoreError("pageNumber", "Not valid input for page number");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testNotValidInputForPageSize() {
        Paging paging = new Paging(1, 0);
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", validOrdering, paging);
        CoreError expectedError = new CoreError("pageSize", "Not valid input for page size");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

}