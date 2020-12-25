package java2.application_target_list.core.validators;

import java2.application_target_list.core.database.Database;
import java2.application_target_list.core.database.TargetListImpl;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ChangeTargetDescriptionValidatorTest {


    ChangeTargetDescriptionValidator validator = new ChangeTargetDescriptionValidator();
    Database database = new TargetListImpl();

    @Test
    public void testValidate_validRequest() {
        database.addTarget(new Target("name", "description", 1));
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(1L, "new description");
        List<CoreError> actualErrors = validator.validate(request, database);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequestId_v2() {
        database.addTarget(new Target("name", "description", 1));
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(-1L, "new description");
        List<CoreError> actualErrors = validator.validate(request, database);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestNewName_v1() {
        database.addTarget(new Target("name", "description", 1));
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(1L, "");
        List<CoreError> actualErrors = validator.validate(request, database);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target new description"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequestNewName_v2() {
        database.addTarget(new Target("name", "description", 1));
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(1L, null);
        List<CoreError> actualErrors = validator.validate(request, database);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target new description"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }
}