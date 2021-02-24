package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.ChangeUserLastNameResponse;
import java2.application_target_list.core.validators.user.ChangeUserLastNameValidator;
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
public class ChangeUserLastNameServiceTest extends TestCase {

    private List<CoreError> errorList;
    @Mock private JpaUserRepository jpaUserRepository;
    @Mock private ChangeUserLastNameValidator changeUserLastNameValidator;
    @InjectMocks private ChangeUserLastNameService changeUserLastNameService;

    @Before
    public void setup() {
        errorList = new ArrayList<>();
    }

//    @Test
//    public void shouldChangeUserLastName() {
////        Mockito.when(userRepository.changeUserLastName(1L, "new surname")).thenReturn(true);
//        Mockito.when(jpaUserRepository.changeUserLastName(1L, "new surname")).thenReturn(1);
//        ChangeUserLastNameRequest changeUserLastNameRequest = new ChangeUserLastNameRequest(1L, "new surname");
//        ChangeUserLastNameResponse changeUserLastNameResponse = changeUserLastNameService.execute(changeUserLastNameRequest);
//        assertFalse(changeUserLastNameResponse.hasErrors());
//    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        errorList.add(new CoreError("User last name", "must not be empty!"));
        ChangeUserLastNameRequest changeUserLastNameRequest = new ChangeUserLastNameRequest(1L, "");
        Mockito.when(changeUserLastNameValidator.validate(changeUserLastNameRequest)).thenReturn(errorList);
        ChangeUserLastNameResponse changeUserLastNameResponse = changeUserLastNameService.execute(changeUserLastNameRequest);
        assertTrue(changeUserLastNameResponse.hasErrors());
        assertEquals(changeUserLastNameResponse.getErrorList().size(), 2);
        assertEquals(changeUserLastNameResponse.getErrorList().get(0).getField(), "User last name");
        assertEquals(changeUserLastNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(changeUserLastNameResponse.getErrorList().get(1).getField(), "User ID;");
        assertEquals(changeUserLastNameResponse.getErrorList().get(1).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        ChangeUserLastNameRequest changeUserLastNameRequest = new ChangeUserLastNameRequest(1L, "new surname");
        Mockito.when(changeUserLastNameValidator.validate(changeUserLastNameRequest)).thenReturn(errorList);
        ChangeUserLastNameResponse changeUserLastNameResponse = changeUserLastNameService.execute(changeUserLastNameRequest);
        assertTrue(changeUserLastNameResponse.hasErrors());
        assertEquals(changeUserLastNameResponse.getErrorList().size(), 1);
        assertEquals(changeUserLastNameResponse.getErrorList().get(0).getField(), "User ID;");
        assertEquals(changeUserLastNameResponse.getErrorList().get(0).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errorList.add(new CoreError("User ID","must not be empty!"));
        ChangeUserLastNameRequest changeUserLastNameRequest = new ChangeUserLastNameRequest(null, "new surname");
        Mockito.when(changeUserLastNameValidator.validate(changeUserLastNameRequest)).thenReturn(errorList);
        ChangeUserLastNameResponse changeUserLastNameResponse = changeUserLastNameService.execute(changeUserLastNameRequest);
        assertTrue(changeUserLastNameResponse.hasErrors());
        assertEquals(changeUserLastNameResponse.getErrorList().size(), 2);
        assertEquals(changeUserLastNameResponse.getErrorList().get(0).getField(), "User ID");
        assertEquals(changeUserLastNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(changeUserLastNameResponse.getErrorList().get(1).getField(), "User ID;");
        assertEquals(changeUserLastNameResponse.getErrorList().get(1).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v4() {
        errorList.add(new CoreError("User ID","must not be negative!"));
        ChangeUserLastNameRequest changeUserLastNameRequest = new ChangeUserLastNameRequest(-2L, "new surname");
        Mockito.when(changeUserLastNameValidator.validate(changeUserLastNameRequest)).thenReturn(errorList);
        ChangeUserLastNameResponse changeUserLastNameResponse = changeUserLastNameService.execute(changeUserLastNameRequest);
        assertTrue(changeUserLastNameResponse.hasErrors());
        assertEquals(changeUserLastNameResponse.getErrorList().size(), 2);
        assertEquals(changeUserLastNameResponse.getErrorList().get(0).getField(), "User ID");
        assertEquals(changeUserLastNameResponse.getErrorList().get(0).getMessage(), "must not be negative!");
        assertEquals(changeUserLastNameResponse.getErrorList().get(1).getField(), "User ID;");
        assertEquals(changeUserLastNameResponse.getErrorList().get(1).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v5() {
        errorList.add(new CoreError("User ID","must not be negative!"));
        errorList.add(new CoreError("User last name", "must not be empty!"));
        ChangeUserLastNameRequest changeUserLastNameRequest = new ChangeUserLastNameRequest(-2L, null);
        Mockito.when(changeUserLastNameValidator.validate(changeUserLastNameRequest)).thenReturn(errorList);
        ChangeUserLastNameResponse changeUserLastNameResponse = changeUserLastNameService.execute(changeUserLastNameRequest);
        assertTrue(changeUserLastNameResponse.hasErrors());
        assertEquals(changeUserLastNameResponse.getErrorList().size(), 3);
        assertEquals(changeUserLastNameResponse.getErrorList().get(0).getField(), "User ID");
        assertEquals(changeUserLastNameResponse.getErrorList().get(0).getMessage(), "must not be negative!");
        assertEquals(changeUserLastNameResponse.getErrorList().get(1).getField(), "User last name");
        assertEquals(changeUserLastNameResponse.getErrorList().get(1).getMessage(), "must not be empty!");
        assertEquals(changeUserLastNameResponse.getErrorList().get(2).getField(), "User ID;");
        assertEquals(changeUserLastNameResponse.getErrorList().get(2).getMessage(), "no user with that ID");
    }
}