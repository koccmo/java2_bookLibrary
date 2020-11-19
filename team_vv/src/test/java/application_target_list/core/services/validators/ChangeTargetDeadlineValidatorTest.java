package application_target_list.core.services.validators;


import application_target_list.core.requests.ChangeTargetDeadlineRequest;
import application_target_list.core.responses.CoreError;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ChangeTargetDeadlineValidatorTest {

    ChangeTargetDeadlineValidator validator = new ChangeTargetDeadlineValidator();

    @Test
    public void testValidate_validRequest() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L,3);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequestNewDeadline() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L,-3);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target new deadline"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("Must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestId() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(-1L,3);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("Must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestIdAndNewDeadline() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(-1L,-3);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("Must not be negative!"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Target new deadline"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("Must not be negative!"));
    }
}