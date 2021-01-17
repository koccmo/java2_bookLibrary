package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.DeleteUserResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.DeleteUserService;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Equality;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class DeleteUserAcceptanceTest {

    private ApplicationContext applicationContext;
    private AddUserService addUserService;
    private GetAllUserService getAllUserService;
    private DeleteUserService deleteUserService;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        addUserService = applicationContext.getBean(AddUserService.class);
        getAllUserService = applicationContext.getBean(GetAllUserService.class);
        deleteUserService = applicationContext.getBean(DeleteUserService.class);

        AddUserRequest addUserRequest1 = new AddUserRequest("name", "surname");
        AddUserRequest addUserRequest2 = new AddUserRequest("name2", "surname2");
        addUserService.execute(addUserRequest1);
        addUserService.execute(addUserRequest2);

    }

    @Test
    public void shouldDeleteUsersFromDB_v1() {
        deleteUserService.execute(new DeleteUserRequest(1L));
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());
        assertEquals(getAllUsersResponse.getUsersList().size(), 1);
        assertNull(getAllUsersResponse.getErrorList());
        assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name2");
        assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname2");
    }

    @Test
    public void shouldDeleteUsersFromDB_v2() {
        deleteUserService.execute(new DeleteUserRequest(2L));
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());
        assertEquals(getAllUsersResponse.getUsersList().size(), 1);
        assertNull(getAllUsersResponse.getErrorList());
        assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
    }

    @Test
    public void shouldDeleteUsersFromDB_v3() {
        deleteUserService.execute(new DeleteUserRequest(2L));
        deleteUserService.execute(new DeleteUserRequest(1L));
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());
        assertEquals(getAllUsersResponse.getUsersList().size(), 0);
        assertNull(getAllUsersResponse.getErrorList());
    }

    @Test
    public void shouldReturnErrorList() {
        DeleteUserResponse deleteUserResponse = deleteUserService.execute(new DeleteUserRequest(3L));
        assertFalse(deleteUserResponse.getErrorList().isEmpty());
        assertEquals(deleteUserResponse.getErrorList().size(), 1);
        assertEquals(deleteUserResponse.getErrorList().get(0).getField(), "User ID;");
        assertEquals(deleteUserResponse.getErrorList().get(0).getMessage(), "no user with that ID");
    }
}
