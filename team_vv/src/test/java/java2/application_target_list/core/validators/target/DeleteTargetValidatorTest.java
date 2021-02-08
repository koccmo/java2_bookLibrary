package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.database.target.InMemoryTargetRepositoryImpl;
import java2.application_target_list.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DeleteTargetValidatorTest {

    private DeleteTargetValidator validator;
    private TargetRepository targetRepository;

    @Before
    public void setup() {
        validator = new DeleteTargetValidator();
        targetRepository = new InMemoryTargetRepositoryImpl();
        targetRepository.addTarget(new Target("name", "description", 1L));
    }
    @Test
    public void testValidate_validRequest() {
       DeleteTargetRequest request = new DeleteTargetRequest(1L);
       List<CoreError> actualErrors = validator.validate(request, targetRepository);
       Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequest(){
        DeleteTargetRequest request = new DeleteTargetRequest(2L);
        List<CoreError> actualErrors = validator.validate(request, targetRepository);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
    }

    @Test
    public void testValidate_invalidRequestNegative(){
        DeleteTargetRequest request = new DeleteTargetRequest(-2L);
        List<CoreError> actualErrors = validator.validate(request, targetRepository);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no target with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
    }
}