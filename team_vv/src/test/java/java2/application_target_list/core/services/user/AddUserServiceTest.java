package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.matchers.UserMatcher;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.validators.user.AddUserValidator;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AddUserServiceTest {

    private List<CoreError> errorList;
    @Mock
    private JpaUserRepository jpaUserRepository;
    @Mock
    private AddUserValidator addUserValidator;
    @InjectMocks
    AddUserService addUserService;

    @BeforeEach
    public void setup() {
        errorList = new ArrayList<>();
    }

    @Test
    public void shouldAddUserToDatabase() {
        Mockito.when(addUserValidator.validate(any())).thenReturn(new ArrayList<>());
        AddUserRequest addUserRequest = new AddUserRequest("name", "surname");
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        Assertions.assertFalse(addUserResponse.hasErrors());
        Mockito.verify(jpaUserRepository).save(argThat(new UserMatcher("name", "surname")));
    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        errorList.add(new CoreError("User first name", "must not be empty!"));
        AddUserRequest addUserRequest = new AddUserRequest("", "surname");
        Mockito.when(addUserValidator.validate(any())).thenReturn(errorList);
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        Assertions.assertTrue(addUserResponse.hasErrors());
        Assertions.assertEquals(addUserResponse.getErrorList().size(), 1);
        Assertions.assertEquals(addUserResponse.getErrorList().get(0).getField(), "User first name");
        Assertions.assertEquals(addUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(jpaUserRepository);
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        errorList.add(new CoreError("User first name", "must not be empty!"));
        AddUserRequest addUserRequest = new AddUserRequest(null, "surname");
        Mockito.when(addUserValidator.validate(any())).thenReturn(errorList);
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        Assertions.assertTrue(addUserResponse.hasErrors());
        Assertions.assertEquals(addUserResponse.getErrorList().size(), 1);
        Assertions.assertEquals(addUserResponse.getErrorList().get(0).getField(), "User first name");
        Assertions.assertEquals(addUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(jpaUserRepository);
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errorList.add(new CoreError("User last name", "must not be empty!"));
        AddUserRequest addUserRequest = new AddUserRequest("name", "");
        Mockito.when(addUserValidator.validate(any())).thenReturn(errorList);
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        Assertions.assertTrue(addUserResponse.hasErrors());
        Assertions.assertEquals(addUserResponse.getErrorList().size(), 1);
        Assertions.assertEquals(addUserResponse.getErrorList().get(0).getField(), "User last name");
        Assertions.assertEquals(addUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(jpaUserRepository);
    }

    @Test
    public void shouldReturnResponseWithErrors_v4() {
        errorList.add(new CoreError("User last name", "must not be empty!"));
        AddUserRequest addUserRequest = new AddUserRequest("name", null);
        Mockito.when(addUserValidator.validate(any())).thenReturn(errorList);
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        Assertions.assertTrue(addUserResponse.hasErrors());
        Assertions.assertEquals(addUserResponse.getErrorList().size(), 1);
        Assertions.assertEquals(addUserResponse.getErrorList().get(0).getField(), "User last name");
        Assertions.assertEquals(addUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(jpaUserRepository);
    }

    @Test
    public void shouldReturnResponseWithErrors_v5() {
        errorList.add(new CoreError("User first name", "must not be empty!"));
        errorList.add(new CoreError("User last name", "must not be empty!"));
        AddUserRequest addUserRequest = new AddUserRequest("", null);
        Mockito.when(addUserValidator.validate(any())).thenReturn(errorList);
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        Assertions.assertTrue(addUserResponse.hasErrors());
        Assertions.assertEquals(addUserResponse.getErrorList().size(), 2);
        Assertions.assertEquals(addUserResponse.getErrorList().get(0).getField(), "User first name");
        Assertions.assertEquals(addUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assertions.assertEquals(addUserResponse.getErrorList().get(1).getField(), "User last name");
        Assertions.assertEquals(addUserResponse.getErrorList().get(1).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(jpaUserRepository);
    }
}