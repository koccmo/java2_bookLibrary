package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.database.user.InMemoryUserRepositoryImpl;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.responses.CoreError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class DeleteUserValidatorTest {

    @Autowired
    private DeleteUserValidator deleteUserValidator;

    @BeforeEach
    public void setup() {
        UserRepository userRepository = new InMemoryUserRepositoryImpl();
        userRepository.addUser(new User("name", "surname"));
    }

    @Test
    public void testValidate_validRequest() {
        DeleteUserRequest request = new DeleteUserRequest(1L);
        List<CoreError> actualErrors = deleteUserValidator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        DeleteUserRequest request = new DeleteUserRequest(-2L);
        List<CoreError> actualErrors = deleteUserValidator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User ID"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
    }
}