package internet_store_tests.core.services.validators_tests.product;

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

        SearchProductRequest searchProductRequest = new SearchProductRequest(null, "", validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidSearchTitleFilled(){

        SearchProductRequest searchProductRequest = new SearchProductRequest("Bob", "", validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testValidSearchDescriptionFilled(){

        SearchProductRequest searchProductRequest = new SearchProductRequest("", "D", validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testValidSearchBothFilled(){

        SearchProductRequest searchProductRequest = new SearchProductRequest("Bob", "Description", validOrdering, validPaging);
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 0);
    }

}