package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.database.user.InMemoryUserRepositoryImpl;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class ChangeUserLastNameValidatorTest {

    @Autowired
    private ChangeUserLastNameValidator changeUserLastNameValidator;

    @BeforeEach
    public void setup() {
        UserRepository userRepository = new InMemoryUserRepositoryImpl();
        userRepository.addUser(new User("name", "surname"));
    }

    @Test
    public void testValidate_validRequest() {
        ChangeUserLastNameRequest request = new ChangeUserLastNameRequest(1L, "new last name");
        List<CoreError> actualErrors = changeUserLastNameValidator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequest_v1() {
        ChangeUserLastNameRequest request = new ChangeUserLastNameRequest(-1L, "new last name");
        List<CoreError> actualErrors = changeUserLastNameValidator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User ID"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequest_v3() {
        ChangeUserLastNameRequest request = new ChangeUserLastNameRequest(1L, "");
        List<CoreError> actualErrors = changeUserLastNameValidator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User new last name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v4() {
        ChangeUserLastNameRequest request = new ChangeUserLastNameRequest(1L, null);
        List<CoreError> actualErrors = changeUserLastNameValidator.validate(request);

        Assertions.assertTrue(actualErrors.get(0).getField().contains("User new last name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v5() {
        ChangeUserLastNameRequest request = new ChangeUserLastNameRequest(-5L, null);
        List<CoreError> actualErrors = changeUserLastNameValidator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 2);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("User ID"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
        Assertions.assertTrue(actualErrors.get(1).getField().contains("User new last name"));
        Assertions.assertTrue(actualErrors.get(1).getMessage().contains("must not be empty!"));
    }
}