package java2.application_target_list.core.validators;

import java2.application_target_list.core.database.Database;
import java2.application_target_list.core.requests.DeleteTargetRequest;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.database.TargetListImpl;
import java2.application_target_list.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DeleteTargetValidatorTest {

    DeleteTargetValidator validator = new DeleteTargetValidator();
    Database database = new TargetListImpl();

    @Test
    public void testValidate_validRequest() {
       database.addTarget(new Target("name", "description", 1));
       DeleteTargetRequest request = new DeleteTargetRequest(1L);
       List<CoreError> actualErrors = validator.validate(request, database);
       Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequest(){
        database.addTarget(new Target("name", "description", 1));
        DeleteTargetRequest request = new DeleteTargetRequest(2L);
        List<CoreError> actualErrors = validator.validate(request, database);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
    }

    @Test
    public void testValidate_invalidRequestNegative(){
        database.addTarget(new Target("name", "description", 1));
        DeleteTargetRequest request = new DeleteTargetRequest(-2L);
        List<CoreError> actualErrors = validator.validate(request, database);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
    }

//    @Test
//    public void testValidate_emptyRequest() {
//       DeleteTargetRequest request = new DeleteTargetRequest(null);
//       List<CoreError> actualErrors = validator.validate(request, database);
//       Assert.assertEquals(actualErrors.size(), 2);
//        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
//        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
//       Assert.assertTrue(actualErrors.get(1).getField().contains("Target ID"));
//       Assert.assertTrue(actualErrors.get(1).getMessage().contains("Must not be empty!"));
//    }
}