package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.database.target.TargetDatabase;
import java2.application_target_list.core.database.target.TargetListImpl;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ChangeTargetDescriptionValidatorTest {

    private ChangeTargetDescriptionValidator validator;
    private TargetDatabase targetDatabase;

    @Before
    public void setup() {
        validator = new ChangeTargetDescriptionValidator();
        targetDatabase = new TargetListImpl();
        targetDatabase.addTarget(new Target("name", "description", 1));
    }

    @Test
    public void testValidate_validRequest() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(1L, "new description");
        List<CoreError> actualErrors = validator.validate(request, targetDatabase);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequestId_v2() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(-1L, "new description");
        List<CoreError> actualErrors = validator.validate(request, targetDatabase);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestNewName_v1() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(1L, "");
        List<CoreError> actualErrors = validator.validate(request, targetDatabase);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target new description"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequestNewName_v2() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(1L, null);
        List<CoreError> actualErrors = validator.validate(request, targetDatabase);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target new description"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }
}