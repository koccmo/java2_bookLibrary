package application_target_list.core.services.validators;

import application_target_list.core.requests.ChangeTargetNameRequest;
import application_target_list.core.responses.CoreError;
import application_target_list.core.services.validators.ChangeTargetNameValidator;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ChangeTargetNameValidatorTest {

    ChangeTargetNameValidator validator = new ChangeTargetNameValidator();

    @Test
    public void testValidate_validRequest() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(1L, "new name");
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

//    @Test
//    public void testValidate_invalidRequestId_v1() {
//        ChangeTargetNameRequest request = new ChangeTargetNameRequest(null, "new name");
//        List<CoreError> actualErrors = validator.validate(request);
//        Assert.assertEquals(actualErrors.size(), 1);
//        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID"));
//        Assert.assertTrue(actualErrors.get(0).getMessage().contains("Must not be empty!"));
//    }

    @Test
    public void testValidate_invalidRequestId_v2() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(-1L, "new name");
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("Must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestNewName_v1() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(1L, "");
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target new name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("Must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequestNewName_v2() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(0L, null);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target new name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("Must not be empty!"));
    }

}