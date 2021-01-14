package java2.application_target_list.core.validators.target;


import java2.application_target_list.core.database.TargetDatabase;
import java2.application_target_list.core.database.TargetListImpl;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.ChangeTargetDeadlineValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ChangeTargetDeadlineValidatorTest {

    private ChangeTargetDeadlineValidator validator;
    private TargetDatabase targetDatabase;

    @Before
    public void setup() {
        validator = new ChangeTargetDeadlineValidator();
        targetDatabase = new TargetListImpl();
        targetDatabase.addTarget(new Target("name", "description", 1));
    }

    @Test
    public void testValidate_validRequest() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L,3);
        List<CoreError> actualErrors = validator.validate(request, targetDatabase);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequestNewDeadline() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L,-3);
        List<CoreError> actualErrors = validator.validate(request, targetDatabase);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target new deadline"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestId() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(-1L,3);
        List<CoreError> actualErrors = validator.validate(request, targetDatabase);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestIdAndNewDeadline() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(-1L,-3);
        List<CoreError> actualErrors = validator.validate(request, targetDatabase);
        Assert.assertEquals(actualErrors.size(), 3);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("Target new deadline"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must not be negative!"));
    }
}