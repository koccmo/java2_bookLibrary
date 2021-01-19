package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.database.target.TargetDatabase;
import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.database.target.TargetListImpl;
import java2.application_target_list.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DeleteTargetValidatorTest {

    private DeleteTargetValidator validator;
    private TargetDatabase targetDatabase;

    @Before
    public void setup() {
        validator = new DeleteTargetValidator();
        targetDatabase = new TargetListImpl();
        targetDatabase.addTarget(new Target("name", "description", 1));
    }
    @Test
    public void testValidate_validRequest() {
       DeleteTargetRequest request = new DeleteTargetRequest(1L);
       List<CoreError> actualErrors = validator.validate(request, targetDatabase);
       Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequest(){
        DeleteTargetRequest request = new DeleteTargetRequest(2L);
        List<CoreError> actualErrors = validator.validate(request, targetDatabase);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
    }

    @Test
    public void testValidate_invalidRequestNegative(){
        DeleteTargetRequest request = new DeleteTargetRequest(-2L);
        List<CoreError> actualErrors = validator.validate(request, targetDatabase);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
    }
}