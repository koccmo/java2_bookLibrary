package application_target_list.core.validators;

import application_target_list.core.database.Database;
import application_target_list.core.database.Target;
import application_target_list.core.database.TargetListImpl;
import application_target_list.core.requests.SearchTargetByDescriptionRequest;
import application_target_list.core.responses.CoreError;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SearchTargetByDescriptionValidatorTest {

    SearchTargetByDescriptionValidator validator = new SearchTargetByDescriptionValidator();
    Database database = new TargetListImpl();

    @Test
    public void testValidate_validRequest() {
        database.addTarget(new Target("name", "description", 1));
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description");
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
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