package java2.application_target_list.core.validators;

import java2.application_target_list.core.database.Database;
import java2.application_target_list.core.database.TargetListImpl;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.SearchTargetByDescriptionRequest;
import java2.application_target_list.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SearchTargetByDescriptionValidatorTest {

    SearchTargetByDescriptionValidator validator = new SearchTargetByDescriptionValidator();
    Database database = new TargetListImpl();

    @Test
    public void testValidate_validRequest_v1() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description");
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v2() {
        database.addTarget(new Target("name", "description", 1));
        Paging paging = new Paging(1,1);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", paging);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v3() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("name", "ASCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v4() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("name", "DESCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v5() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("description", "ASCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v6() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("description", "DESCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v7() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("deadline", "ASCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v8() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("deadline", "DESCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequestWithPaging_v1() {
        database.addTarget(new Target("name", "description", 1));
        Paging paging =  new Paging(0,0);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", paging);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Page size"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithPaging_v2() {
        database.addTarget(new Target("name", "description", 1));
        Paging paging = new Paging(3,0);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", paging);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page size"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithPaging_v3() {
        database.addTarget(new Target("name", "description", 1));
        Paging paging = new Paging(0,1);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", paging);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v1() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("", "");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 4);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain NAME, DESCRIPTION or DEADLINE only!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(3).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(3).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v2() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("", "ASCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain NAME, DESCRIPTION or DEADLINE only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v3() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("na", "DESCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must contain NAME, DESCRIPTION or DEADLINE only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v4() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("name", "DESCEND");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v5() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("name", "");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrderingAndPaging_v1() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("", "");
        Paging paging = new Paging(0,0);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering, paging);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 6);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain NAME, DESCRIPTION or DEADLINE only!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(3).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(3).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
        Assert.assertTrue(actualErrors.get(4).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(4).getMessage().contains("must be greater then 0!"));
        Assert.assertTrue(actualErrors.get(5).getField().contains("Page size"));
        Assert.assertTrue(actualErrors.get(5).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrderingAndPaging_v2() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("", "");
        Paging paging = new Paging(0,1);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering, paging);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 5);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain NAME, DESCRIPTION or DEADLINE only!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(3).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(3).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
        Assert.assertTrue(actualErrors.get(4).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(4).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrderingAndPaging_v3() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("name", "");
        Paging paging = new Paging(0,1);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering, paging);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 3);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrderingAndPaging_v4() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("name", "");
        Paging paging = new Paging(1,1);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering, paging);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
    }

    @Test
    public void testValidate_invalidRequest_v1() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest(null);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target description"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("");
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target description"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }
}