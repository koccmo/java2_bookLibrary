package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.database.user.InMemoryUserRepositoryImpl;
import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.UpdateUserRequest;
import java2.application_target_list.core.responses.CoreError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class UpdateUserValidatorTest {

    @Autowired
    private UpdateUserValidator updateUserValidator;

    @BeforeEach
    public void setup() {
        UserRepository userRepository = new InMemoryUserRepositoryImpl();
        userRepository.addUser(new User("name", "surname"));
    }

    @Test
    public void testValidate_validRequest() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, "new first name", "new last name");
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assertions.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequest_v1() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(-1L, "new first name", "new last name");
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User ID"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, "new first name", "");
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User new last name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }


    @Test
    public void testValidate_invalidRequest_v3() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, "", "new last name");
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User new first name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v4() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, null, "new last name");
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User new first name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v5() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, "new first name", null);
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User new last name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v6() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(-1L, null, "new last name");
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assertions.assertEquals(actualErrors.size(), 2);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User ID"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
        Assertions.assertTrue(actualErrors.get(1).getField().contains("User new first name"));
        Assertions.assertTrue(actualErrors.get(1).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v7() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(-1L, "", null);
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assertions.assertEquals(actualErrors.size(), 3);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User ID"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
        Assertions.assertTrue(actualErrors.get(2).getField().contains("User new first name"));
        Assertions.assertTrue(actualErrors.get(2).getMessage().contains("must not be empty!"));
        Assertions.assertTrue(actualErrors.get(1).getField().contains("User new last name"));
        Assertions.assertTrue(actualErrors.get(1).getMessage().contains("must not be empty!"));
    }
}