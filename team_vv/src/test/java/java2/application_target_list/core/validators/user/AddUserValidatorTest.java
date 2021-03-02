package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.CoreError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class AddUserValidatorTest {

    @Autowired
    private AddUserValidator addUserValidator;

    @Test
    public void testValidate_validRequest() {
        AddUserRequest request = new AddUserRequest("name", "surname");
        List<CoreError> actualErrors = addUserValidator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidFirstNameRequest_v1() {
        AddUserRequest request = new AddUserRequest(null, "surname");
        List<CoreError> actualErrors = addUserValidator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User first name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidFirstNameRequest_v2() {
        AddUserRequest request = new AddUserRequest("", "surname");
        List<CoreError> actualErrors = addUserValidator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User first name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidLastNameRequest_v1() {
        AddUserRequest request = new AddUserRequest("name", "");
        List<CoreError> actualErrors = addUserValidator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User last name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidLastNameRequest_v2() {
        AddUserRequest request = new AddUserRequest("name", null);
        List<CoreError> actualErrors = addUserValidator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User last name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidFirstAndLastNameRequest() {
        AddUserRequest request = new AddUserRequest("", null);
        List<CoreError> actualErrors = addUserValidator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 2);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User first name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
        Assertions.assertTrue(actualErrors.get(1).getField().contains("User last name"));
        Assertions.assertTrue(actualErrors.get(1).getMessage().contains("must not be empty!"));
    }
}