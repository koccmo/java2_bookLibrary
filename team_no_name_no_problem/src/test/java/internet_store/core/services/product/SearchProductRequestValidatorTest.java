package internet_store.core.services.product;

import internet_store.core.requests.customer.SearchProductRequest;
import internet_store.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchProductRequestValidatorTest {

    SearchProductRequestValidator searchProductRequestValidator = new SearchProductRequestValidator();


    @Test
    public void testEmptySearch(){
        CoreError expectedError = new CoreError("title & description", "Not valid input for search");

        SearchProductRequest searchProductRequest = new SearchProductRequest(null, "");
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidSearchTitleFilled(){

        SearchProductRequest searchProductRequest = new SearchProductRequest("Bob", "");
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testValidSearchDescriptionFilled(){

        SearchProductRequest searchProductRequest = new SearchProductRequest("", "D");
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testValidSearchBothFilled(){

        SearchProductRequest searchProductRequest = new SearchProductRequest("Bob", "Description");
        List<CoreError> errors= searchProductRequestValidator.validate(searchProductRequest);

        assertTrue(errors.size() == 0);
    }

}