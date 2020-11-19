package application_target_list.core.services.validators;

import application_target_list.core.requests.ChangeTargetDescriptionRequest;
import application_target_list.core.responses.CoreError;
import application_target_list.core.services.validators.ChangeTargetDescriptionValidator;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ChangeTargetDescriptionValidatorTest {


    ChangeTargetDescriptionValidator validator = new ChangeTargetDescriptionValidator();

    @Test
    public void testValidate_validRequest() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(1L, "new description");
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequestId_v2() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(-1L, "new description");
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("Must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestNewName_v1() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(1L, "");
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target new description"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("Must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequestNewName_v2() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(1L, null);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target new description"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("Must not be empty!"));
    }
}