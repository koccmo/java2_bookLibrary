package internet_store_tests.core.validators_tests.product;

import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.product.SearchProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.services.product.validators.SearchProductRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchProductRequestValidatorTest {

    SearchProductRequestValidator searchProductRequestValidator = new SearchProductRequestValidator();
    Ordering validOrdering = new Ordering("title", "ASC");
    Paging validPaging = new Paging(1, 1);


    @Test
    public void testEmptySearch(){
        CoreError expectedError = new CoreError("search", "Not valid input for search");

        SearchProductRequest searchProductRequest = new SearchProductRequest(null, "",null,null, validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidSearchTitleFilled(){

        SearchProductRequest searchProductRequest = new SearchProductRequest("Bob", "",null,null, validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 0);
    }
/*
    @Test
    public void testValidSearchDescriptionFilled(){

        SearchProductRequest searchProductRequest = new SearchProductRequest("", "D",null,null, validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 0);
    }
*/
    @Test
    public void testValidSearchTitleAndDescriptionFilled(){

        SearchProductRequest searchProductRequest = new SearchProductRequest("Bob", "Description",null,null, validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testValidSearchPriceRangeFilled(){

        SearchProductRequest searchProductRequest = new SearchProductRequest("", "",300,400, validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testNotValidSearchStartPriceIsNotEntered() {

        SearchProductRequest searchProductRequest = new SearchProductRequest("", "",null,400, validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 1);
    }

    @Test
    public void testNotValidSearchEndPriceIsNotEntered() {

        SearchProductRequest searchProductRequest = new SearchProductRequest("", "",300,null, validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 1);
    }
}

