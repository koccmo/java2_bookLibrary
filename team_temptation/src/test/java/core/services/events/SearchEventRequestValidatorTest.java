package core.services.events;

import core.requests.Ordering;
import core.requests.Paging;
import core.requests.events.SearchEventRequest;
import core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchEventRequestValidatorTest {

    private SearchEventRequestValidator validator = new SearchEventRequestValidator();

    @Test
    void shouldNotReturnErrorsWhenOrderByAndOrderDirectionBothDefined() {

        Ordering order = new Ordering("orderBy", "orderDirect");
        Paging paging = new Paging(1, 20);
        SearchEventRequest request = new SearchEventRequest("", "", 0, order, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    void shouldNotReturnErrorsWhenPageNumberAboveZero() {

        Ordering order = new Ordering("", "");
        Paging paging = new Paging(10, 20);
        SearchEventRequest request = new SearchEventRequest("", "", 0, order, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    void shouldNotReturnErrorsWhenPageSizeEqualEight() {

        Ordering order = new Ordering("", "");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("", "", 0, order, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
    @Test
    void shouldNotReturnErrorsWhenPageSizeEqualThirty() {

        Ordering order = new Ordering("", "");
        Paging paging = new Paging(1, 30);
        SearchEventRequest request = new SearchEventRequest("", "", 0, order, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    void shouldReturnErrorsWhenPageNumberLessThanZero() {

        Ordering order = new Ordering("", "");
        Paging paging = new Paging(-1, 20);
        SearchEventRequest request = new SearchEventRequest("", "", 0, order, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "pageNumber");
        assertEquals(errors.get(0).getErrorMessage(), "Must be above zero");
    }

    @Test
    void shouldReturnErrorsWhenPageNumberEqualZero() {

        Ordering order = new Ordering("", "");
        Paging paging = new Paging(0, 20);
        SearchEventRequest request = new SearchEventRequest("", "", 0, order, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "pageNumber");
        assertEquals(errors.get(0).getErrorMessage(), "Must be above zero");
    }

    @Test
    void shouldReturnErrorsWhenPageSizeLessThanEight() {

        Ordering order = new Ordering("", "");
        Paging paging = new Paging(1, 7);
        SearchEventRequest request = new SearchEventRequest("", "", 0, order, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "pageSize");
        assertEquals(errors.get(0).getErrorMessage(), "Must be between 8 and 30");
    }

    @Test
    void shouldReturnErrorsWhenPageSizeBiggerThanThirty() {

        Ordering order = new Ordering("", "");
        Paging paging = new Paging(1, 31);
        SearchEventRequest request = new SearchEventRequest("", "", 0, order, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "pageSize");
        assertEquals(errors.get(0).getErrorMessage(), "Must be between 8 and 30");
    }

    @Test
    void shouldReturnTwoErrorsAboutPageNumberAndPageSize() {

        Ordering order = new Ordering("", "");
        Paging paging = new Paging(-1, 31);
        SearchEventRequest request = new SearchEventRequest("", "", 0, order, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getErrorField(), "pageNumber");
        assertEquals(errors.get(0).getErrorMessage(), "Must be above zero");
        assertEquals(errors.get(1).getErrorField(), "pageSize");
        assertEquals(errors.get(1).getErrorMessage(), "Must be between 8 and 30");
    }

    @Test
    void shouldReturnThreeErrorsAboutOrderingPageNumberAndPageSize() {

        Ordering order = new Ordering("orderBy", "");
        Paging paging = new Paging(-1, 31);
        SearchEventRequest request = new SearchEventRequest("", "", 0, order, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 3);
        assertEquals(errors.get(0).getErrorField(), "orderDirect");
        assertEquals(errors.get(0).getErrorMessage(), "Must be specified as the sort criteria is specified");
        assertEquals(errors.get(1).getErrorField(), "pageNumber");
        assertEquals(errors.get(1).getErrorMessage(), "Must be above zero");
        assertEquals(errors.get(2).getErrorField(), "pageSize");
        assertEquals(errors.get(2).getErrorMessage(), "Must be between 8 and 30");
    }

    @Test
    void shouldReturnErrorsWhenOrderByDefinedAndOrderDirectionNot() {

        Ordering order = new Ordering("orderBy", "");
        Paging paging = new Paging(1, 20);
        SearchEventRequest request = new SearchEventRequest("", "", 0, order, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "orderDirect");
        assertEquals(errors.get(0).getErrorMessage(), "Must be specified as the sort criteria is specified");
    }

    @Test
    void shouldReturnErrorsWhenOrderDirectionDefinedAndOrderByNot() {

        Ordering order = new Ordering("", "orderDirect");
        Paging paging = new Paging(1, 20);
        SearchEventRequest request = new SearchEventRequest("", "", 0, order, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "orderBy");
        assertEquals(errors.get(0).getErrorMessage(), "Must be specified as the sort order is specified");
    }

}