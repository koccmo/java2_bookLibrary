package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.UserDatabase;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GetAllUserServiceTest extends TestCase {

    private List<User> userList;
    @Mock private UserDatabase userDatabase;
    @InjectMocks private GetAllUserService getAllUserService;

    @Before
    public void setup() {
        userList = new ArrayList<>();
    }

    @Test
    public void shouldReturnDB_v1() {
        userList.add(new User("name", "surname"));
        Mockito.when(userDatabase.getUsersList()).thenReturn(userList);
        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(getAllUsersRequest);
        assertFalse(getAllUsersResponse.getUsersList().isEmpty());
        assertFalse(getAllUsersResponse.hasErrors());
        assertEquals(getAllUsersResponse.getUsersList().size(), 1);
        assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
    }

    @Test
    public void shouldReturnDB_v2() {
        userList.add(new User("name", "surname"));
        userList.add(new User("name1", "surname2"));
        Mockito.when(userDatabase.getUsersList()).thenReturn(userList);
        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(getAllUsersRequest);
        assertFalse(getAllUsersResponse.getUsersList().isEmpty());
        assertFalse(getAllUsersResponse.hasErrors());
        assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name1");
        assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "surname2");
    }    @Test
    public void shouldReturnDB_v3() {
        Mockito.when(userDatabase.getUsersList()).thenReturn(userList);
        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(getAllUsersRequest);
        assertTrue(getAllUsersResponse.getUsersList().isEmpty());
        assertFalse(getAllUsersResponse.hasErrors());
    }
}