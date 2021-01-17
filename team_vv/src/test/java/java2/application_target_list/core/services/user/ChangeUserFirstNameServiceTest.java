package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.user.UserDatabase;
import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.ChangeUserFirstNameResponse;
import java2.application_target_list.core.validators.user.ChangeUserFirstNameValidator;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ChangeUserFirstNameServiceTest extends TestCase {

    private List<CoreError> errorList;
    @Mock private UserDatabase userDatabase;
    @Mock private ChangeUserFirstNameValidator changeUserFirstNameValidator;
    @InjectMocks private ChangeUserFirstNameService changeUserFirstNameService;

    @Before
    public void setup() {
        errorList = new ArrayList<>();
    }

    @Test
    public void shouldChangeUserName() {
        Mockito.when(userDatabase.changeUserFirstName(1L, "new name")).thenReturn(true);
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(1L, "new name");
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        assertFalse(changeUserFirstNameResponse.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        errorList.add(new CoreError("User ID;","no user with that ID"));
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(2L, "new name");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest, userDatabase)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        assertTrue(changeUserFirstNameResponse.hasErrors());
        assertEquals(changeUserFirstNameResponse.getErrorList().size(), 1);
        assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User ID;");
        assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        errorList.add(new CoreError("User ID","must not be empty!"));
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(null, "new name");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest, userDatabase)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        assertTrue(changeUserFirstNameResponse.hasErrors());
        assertEquals(changeUserFirstNameResponse.getErrorList().size(), 1);
        assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User ID");
        assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errorList.add(new CoreError("User ID","must not be negative!"));
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(-3L, "new name");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest, userDatabase)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        assertTrue(changeUserFirstNameResponse.hasErrors());
        assertEquals(changeUserFirstNameResponse.getErrorList().size(), 1);
        assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User ID");
        assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v4() {
        errorList.add(new CoreError("User new first name","must not be empty!"));
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(1L, "");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest, userDatabase)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        assertTrue(changeUserFirstNameResponse.hasErrors());
        assertEquals(changeUserFirstNameResponse.getErrorList().size(), 1);
        assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User new first name");
        assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v5() {
        errorList.add(new CoreError("User new first name","must not be empty!"));
        errorList.add(new CoreError("User ID","must not be negative!"));
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(-1L, "");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest, userDatabase)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        assertTrue(changeUserFirstNameResponse.hasErrors());
        assertEquals(changeUserFirstNameResponse.getErrorList().size(), 2);
        assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User new first name");
        assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getField(), "User ID");
        assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getMessage(), "must not be negative!");
    }
}