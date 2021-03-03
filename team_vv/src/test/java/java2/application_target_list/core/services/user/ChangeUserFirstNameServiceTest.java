package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.ChangeUserFirstNameResponse;
import java2.application_target_list.core.validators.user.ChangeUserFirstNameValidator;
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

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class ChangeUserFirstNameServiceTest {

    private List<CoreError> errorList;
    @Mock
    private JpaUserRepository jpaUserRepository;
    @Mock
    private ChangeUserFirstNameValidator changeUserFirstNameValidator;
    @InjectMocks
    ChangeUserFirstNameService changeUserFirstNameService;

    @BeforeEach
    public void setup() {
        errorList = new ArrayList<>();
    }

    @Test
    public void shouldChangeUserName() {
        Mockito.when(jpaUserRepository.existsById(1L)).thenReturn(true);
        Mockito.when(jpaUserRepository.changeUserFirstName(1L, "new name")).thenReturn(1);
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(1L, "new name");
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assertions.assertFalse(changeUserFirstNameResponse.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(2L, "new name");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assertions.assertTrue(changeUserFirstNameResponse.hasErrors());
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().size(), 1);
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User ID;");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        errorList.add(new CoreError("User ID","must not be empty!"));
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(null, "new name");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assertions.assertTrue(changeUserFirstNameResponse.hasErrors());
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().size(), 2);
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User ID");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getField(), "User ID;");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errorList.add(new CoreError("User ID","must not be negative!"));
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(-3L, "new name");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assertions.assertTrue(changeUserFirstNameResponse.hasErrors());
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().size(), 2);
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User ID");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "must not be negative!");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getField(), "User ID;");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v4() {
        errorList.add(new CoreError("User new first name","must not be empty!"));
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(1L, "");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assertions.assertTrue(changeUserFirstNameResponse.hasErrors());
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().size(), 2);
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User new first name");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getField(), "User ID;");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v5() {
        errorList.add(new CoreError("User new first name","must not be empty!"));
        errorList.add(new CoreError("User ID","must not be negative!"));
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(-1L, "");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assertions.assertTrue(changeUserFirstNameResponse.hasErrors());
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().size(), 3);
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User new first name");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getField(), "User ID");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getMessage(), "must not be negative!");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(2).getField(), "User ID;");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(2).getMessage(), "no user with that ID");
    }
}