package application_target_list.core.services.validators;


import application_target_list.Target;
import application_target_list.core.database.Database;
import application_target_list.core.database.TargetListImpl;
import application_target_list.core.requests.ChangeTargetDeadlineRequest;
import application_target_list.core.responses.CoreError;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ChangeTargetDeadlineValidatorTest {

    ChangeTargetDeadlineValidator validator = new ChangeTargetDeadlineValidator();
    Database database = new TargetListImpl();


    @Test
    public void testValidate_validRequest() {
        database.addTarget(new Target("name", "description", 1));
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L,3);
        List<CoreError> actualErrors = validator.validate(request, database);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequestNewDeadline() {
        database.addTarget(new Target("name", "description", 1));
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L,-3);
        List<CoreError> actualErrors = validator.validate(request, database);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target new deadline"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestId() {
        database.addTarget(new Target("name", "description", 1));
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(-1L,3);
        List<CoreError> actualErrors = validator.validate(request, database);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestIdAndNewDeadline() {
        database.addTarget(new Target("name", "description", 1));
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(-1L,-3);
        List<CoreError> actualErrors = validator.validate(request, database);
        Assert.assertEquals(actualErrors.size(), 3);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("Target new deadline"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must not be negative!"));
    }
}