package book_library.core.validators.Reader;

import book_library.core.requests.Ordering;
import book_library.core.requests.Paging;
import book_library.core.requests.Reader.SearchReaderRequest;
import book_library.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchReadersRequestValidatorTest {

    private SearchReadersRequestValidator validator = new SearchReadersRequestValidator();

    @Test
    public void successWithFirstName(){
        SearchReaderRequest request = new SearchReaderRequest("FirstName", null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void successWithLastName(){
        SearchReaderRequest request = new SearchReaderRequest(null, "LastName", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void successWithPersonalCode(){
        SearchReaderRequest request = new SearchReaderRequest(null, null, 11111111111L);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void successWithFirstNameAndLastNameAndPersonalCode(){
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", 11111111111L);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenFirstNameAndLastNameAndPersonalCodeAreNull(){
        SearchReaderRequest request = new SearchReaderRequest(null, null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(3, errors.size());
        assertEquals("firstName", errors.get(0).getField());
        assertEquals( "Must not be empty", errors.get(0).getMessage());
        assertEquals("lastName", errors.get(1).getField());
        assertEquals( "Must not be empty", errors.get(1).getMessage());
        assertEquals("personalCode", errors.get(2).getField());
        assertEquals( "Must not be empty", errors.get(2).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenFirstNameIsEmpty(){
        SearchReaderRequest request = new SearchReaderRequest("", null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(3, errors.size());
        assertEquals("firstName", errors.get(0).getField());
        assertEquals( "Must not be empty", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenLastNameIsEmpty(){
        SearchReaderRequest request = new SearchReaderRequest(null, "", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(3, errors.size());
        assertEquals("firstName", errors.get(0).getField());
        assertEquals( "Must not be empty", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPersonalCodeIsNotCorrect(){
        SearchReaderRequest request = new SearchReaderRequest(null, null, 1L);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("personalCode", errors.get(0).getField());
        assertEquals( "Must contain 11 digits", errors.get(0).getMessage());
    }

    @Test
    public void successWithOrdering() {
        Ordering ordering = new Ordering("firstName", "ASCENDING");
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", 11111111111L, ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenOrderByIsNullAndOrderDirectionContainValidValue() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", 11111111111L, ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("orderBy", errors.get(0).getField());
        assertEquals( "Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOrderByIsNullAndOrderDirectionContainNotValidValue() {
        Ordering ordering = new Ordering(null,"notValidValue");
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", 11111111111L, ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals( 2, errors.size());
        assertEquals("orderDirection", errors.get(0).getField());
        assertEquals( "Must contain 'ASCENDING' or 'DESCENDING' only!", errors.get(0).getMessage());
        assertEquals("orderBy", errors.get(1).getField());
        assertEquals( "Must not be empty!", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainValidValueAndOrderDirectionIsNull() {
        Ordering ordering = new Ordering("firstName",null);
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", 11111111111L, ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals( 1, errors.size());
        assertEquals("orderDirection", errors.get(0).getField());
        assertEquals( "Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainsNotValidValueAndOrderDirectionIsNull() {
        Ordering ordering = new Ordering("notValidValue",null);
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", 11111111111L, ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals( 2, errors.size());
        assertEquals("orderBy", errors.get(0).getField());
        assertEquals( "Must contain 'firstName', 'lastName' or 'personalCode' only!", errors.get(0).getMessage());
        assertEquals("orderDirection", errors.get(1).getField());
        assertEquals( "Must not be empty!", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        Ordering ordering = new Ordering("firstName", "noValidValue");
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", 11111111111L, ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("orderDirection", errors.get(0).getField());
        assertEquals("Must contain 'ASCENDING' or 'DESCENDING' only!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        Ordering ordering = new Ordering("noValidValue","ASCENDING");
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", 11111111111L, ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals( 1, errors.size());
        assertEquals("orderBy", errors.get(0).getField());
        assertEquals( "Must contain 'firstName', 'lastName' or 'personalCode' only!", errors.get(0).getMessage());
    }

    @Test
    public void successWithPaging() {
        Paging paging = new Paging(1, 1);
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", 11111111111L, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenPageNumberContainNotValidValueAndPageSizeContainValidValue() {
        Paging paging = new Paging(0, 1);
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", 11111111111L, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("pageNumber", errors.get(0).getField());
        assertEquals( "Must be greater then 0!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPageNumberContainValidValueAndPageSizeContainNotValidValue() {
        Paging paging = new Paging(1, 0);
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", 11111111111L, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("pageSize", errors.get(0).getField());
        assertEquals( "Must be greater then 0!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPageNumberIsNullAndPageSizeContainValidValue() {
        Paging paging = new Paging(null, 1);
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", 11111111111L, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("pageNumber", errors.get(0).getField());
        assertEquals( "Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPageNumberContainValidValueAndPageSizeIsNull() {
        Paging paging = new Paging(1, null);
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", 11111111111L, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("pageSize", errors.get(0).getField());
        assertEquals( "Must not be empty!", errors.get(0).getMessage());
    }

}