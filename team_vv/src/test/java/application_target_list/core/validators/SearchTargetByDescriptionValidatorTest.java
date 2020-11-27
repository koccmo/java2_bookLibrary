package application_target_list.core.validators;

import application_target_list.core.database.Database;
import application_target_list.core.database.Target;
import application_target_list.core.database.TargetListImpl;
import application_target_list.core.requests.Paging;
import application_target_list.core.requests.SearchTargetByDescriptionRequest;
import application_target_list.core.responses.CoreError;
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
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", new Paging(1,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequest_v5() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", new Paging(0,0));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Page size"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequest_v4() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", new Paging(3,0));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page size"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequest_v3() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", new Paging(0,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
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