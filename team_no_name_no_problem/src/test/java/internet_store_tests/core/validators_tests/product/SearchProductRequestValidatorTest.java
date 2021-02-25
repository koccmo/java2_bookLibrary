package internet_store_tests.core.validators_tests.product;

import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.product.SearchProductByOtherRequest;
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

        SearchProductByOtherRequest searchProductRequest = new SearchProductByOtherRequest(null, "",null,null, validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidSearchTitleFilled(){

        SearchProductByOtherRequest searchProductRequest = new SearchProductByOtherRequest("Bob", "",null,null, validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testValidSearchDescriptionFilled(){

        SearchProductByOtherRequest searchProductRequest = new SearchProductByOtherRequest("", "D",null,null, validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testValidSearchTitleAndDescriptionFilled(){

        SearchProductByOtherRequest searchProductRequest = new SearchProductByOtherRequest("Bob", "Description",null,null, validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testValidSearchPriceRangeFilled(){

        SearchProductByOtherRequest searchProductRequest = new SearchProductByOtherRequest("", "",300,400, validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testNotValidSearchStartPriceIsNotEntered() {

        SearchProductByOtherRequest searchProductRequest = new SearchProductByOtherRequest("", "",null,400, validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 1);
    }

    @Test
    public void testNotValidSearchEndPriceIsNotEntered() {

        SearchProductByOtherRequest searchProductRequest = new SearchProductByOtherRequest("", "",300,null, validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 1);
    }
}

