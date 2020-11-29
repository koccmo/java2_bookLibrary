package application_target_list.core.validators;

import application_target_list.core.requests.Ordering;
import application_target_list.core.requests.Paging;
import application_target_list.core.requests.SearchTargetByNameRequest;
import application_target_list.core.database.Database;
import application_target_list.core.database.Target;
import application_target_list.core.database.TargetListImpl;
import application_target_list.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SearchTargetByNameValidatorTest {

    SearchTargetByNameValidator validator = new SearchTargetByNameValidator();
    Database database = new TargetListImpl();

    @Test
    public void testValidate_validRequest_v1() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name");
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestPaging_v1() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", new Paging(1,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestPagingAndOrdering_v1() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name",
                                            new Ordering("name", "ASCENDING"),
                                            new Paging(1,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestPagingAndOrdering_v2() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name",
                                            new Ordering("name", "DESCENDING"),
                                            new Paging(1,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestPagingAndOrdering_v3() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name",
                                            new Ordering("description", "DESCENDING"),
                                            new Paging(1,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestPagingAndOrdering_v4() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name",
                                            new Ordering("description", "ASCENDING"),
                                            new Paging(1,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestPagingAndOrdering_v5() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name",
                                            new Ordering("deadline", "ASCENDING"),
                                            new Paging(1,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestPagingAndOrdering_v6() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name",
                                            new Ordering("deadline", "DESCENDING"),
                                            new Paging(1,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestOrdering_v1() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", new Ordering("name", "ASCENDING"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestOrdering_v2() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", new Ordering("name", "DESCENDING"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestOrdering_v3() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", new Ordering("description", "DESCENDING"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestOrdering_v4() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", new Ordering("description", "ASCENDING"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestOrdering_v5() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", new Ordering("deadline", "DESCENDING"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestOrdering_v6() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", new Ordering("deadline", "ASCENDING"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequestPaging_v1() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", new Paging(0,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestPaging_v2() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", new Paging(4,0));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page size"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestPaging_v3() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", new Paging(0,0));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Page size"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestOrdering_v1() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("", "ASCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain NAME, DESCRIPTION or DEADLINE only!"));
    }

    @Test
    public void testValidate_invalidRequestOrdering_v2() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("nam", "ASCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must contain NAME, DESCRIPTION or DEADLINE only!"));
    }

    @Test
    public void testValidate_invalidRequestOrdering_v3() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("name", "ASC");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
    }

    @Test
    public void testValidate_invalidRequestOrdering_v4() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("name", "");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
    }

    @Test
    public void testValidate_invalidRequestOrdering_v5() {
        database.addTarget(new Target("name", "description", 1));
        Ordering ordering = new Ordering("", "");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
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
    public void testValidate_invalidRequest_v1() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest(null);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("");
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

}