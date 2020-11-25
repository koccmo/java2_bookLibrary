package application_target_list.core.validators;

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
    public void testValidate_validRequest_v2() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", new Paging(1,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v3() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", new Paging(0,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_validRequest_v4() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", new Paging(4,0));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page size"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_validRequest_v5() {
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