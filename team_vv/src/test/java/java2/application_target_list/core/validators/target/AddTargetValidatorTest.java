package java2.application_target_list.core.validators.target;


import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class AddTargetValidatorTest {

    @Autowired
    private AddTargetValidator validator;


    @Test
    public void testValidate_validRequest() {
        AddTargetRequest request = new AddTargetRequest("name", "description", 2L);
        List<CoreError> actualErrors = validator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidNameRequest_v1() {
        AddTargetRequest request = new AddTargetRequest("", "description", 2L);
        List<CoreError> actualErrors = validator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("Target name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidNameRequest_v2() {
        AddTargetRequest request = new AddTargetRequest(null, "description", 2L);
        List<CoreError> actualErrors = validator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("Target name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidDescriptionRequest_v1() {
        AddTargetRequest request = new AddTargetRequest("name", "", 2L);
        List<CoreError> actualErrors = validator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("Target description"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidDescriptionRequest_v2() {
        AddTargetRequest request = new AddTargetRequest("name", null, 2L);
        List<CoreError> actualErrors = validator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("Target description"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidDeadlineRequest_v1() {
        AddTargetRequest request = new AddTargetRequest("name", "description", -2L);
        List<CoreError> actualErrors = validator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("Target deadline"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidDeadlineNameDescriptionRequest() {
        AddTargetRequest request = new AddTargetRequest("", null, -2L);
        List<CoreError> actualErrors = validator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 3);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("Target name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
        Assertions.assertTrue(actualErrors.get(1).getField().contains("Target description"));
        Assertions.assertTrue(actualErrors.get(1).getMessage().contains("must not be empty!"));
        Assertions.assertTrue(actualErrors.get(2).getField().contains("Target deadline"));
        Assertions.assertTrue(actualErrors.get(2).getMessage().contains("must not be negative!"));
    }
}