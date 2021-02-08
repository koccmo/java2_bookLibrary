package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.database.target.InMemoryTargetRepositoryImpl;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import java2.application_target_list.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ChangeTargetNameValidatorTest {

    private ChangeTargetNameValidator validator;
    private TargetRepository targetRepository;

    @Before
    public void setup() {
        validator = new ChangeTargetNameValidator();
        targetRepository = new InMemoryTargetRepositoryImpl();
        targetRepository.addTarget(new Target("name", "description", 1L));
    }
    @Test
    public void testValidate_validRequest() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(1L, "new name");
        List<CoreError> actualErrors = validator.validate(request, targetRepository);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequestId() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(-1L, "new name");
        List<CoreError> actualErrors = validator.validate(request, targetRepository);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestNewName_v1() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(1L, "");
        List<CoreError> actualErrors = validator.validate(request, targetRepository);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target new name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequestNewName_v2() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(0L, null);
        List<CoreError> actualErrors = validator.validate(request, targetRepository);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Target new name"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be empty!"));
    }

}