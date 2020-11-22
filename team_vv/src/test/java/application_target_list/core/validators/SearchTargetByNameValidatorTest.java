package application_target_list.core.validators;

import application_target_list.core.requests.SearchTargetByNameRequest;
import application_target_list.core.database.Database;
import application_target_list.core.database.Target;
import application_target_list.core.database.TargetListImpl;
import application_target_list.core.responses.CoreError;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SearchTargetByNameValidatorTest {

    SearchTargetByNameValidator validator = new SearchTargetByNameValidator();
    Database database = new TargetListImpl();

    @Test
    public void testValidate_validRequest() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name");
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
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