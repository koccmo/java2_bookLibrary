package application_target_list.core.services.validators;

import application_target_list.core.requests.DeleteTargetRequest;
import application_target_list.core.responses.CoreError;
import application_target_list.core.services.validators.DeleteTargetValidator;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DeleteTargetValidatorTest {

    DeleteTargetValidator validator = new DeleteTargetValidator();

    @Test
    public void testValidate_validRequest() {
       DeleteTargetRequest request = new DeleteTargetRequest(1L);
       List<CoreError> actualErrors = validator.validate(request);
       Assert.assertEquals(actualErrors.size(), 0);
    }

//    @Test
//    public void testValidate_invalidRequest() {
//       DeleteTargetRequest request = new DeleteTargetRequest(null);
//       List<CoreError> actualErrors = validator.validate(request);
//       Assert.assertEquals(actualErrors.size(), 1);
//       Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID"));
//       Assert.assertTrue(actualErrors.get(0).getMessage().contains("Must not be empty!"));
//    }
}