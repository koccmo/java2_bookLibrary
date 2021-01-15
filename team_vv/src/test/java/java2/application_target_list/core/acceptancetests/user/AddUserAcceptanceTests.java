package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class AddUserAcceptanceTests {

    private ApplicationContext applicationContext;
    private AddUserService addUserService;
    private GetAllUserService getAllUserService;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        addUserService = applicationContext.getBean(AddUserService.class);
        getAllUserService = applicationContext.getBean(GetAllUserService.class);
    }

    @Test
    public void shouldAddUsersToDB() {
        AddUserRequest addUserRequest1 = new AddUserRequest("name", "surname");
        AddUserRequest addUserRequest2 = new AddUserRequest("name2", "surname2");
        addUserService.execute(addUserRequest1);
        addUserService.execute(addUserRequest2);
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());
        assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        assertNull(getAllUsersResponse.getErrorList());
        assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name2");
        assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "surname2");
    }

    @Test
    public void shouldReturnErrorList() {
        AddUserRequest addUserRequest = new AddUserRequest("", "surname2");
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        assertEquals(addUserResponse.getErrorList().size(), 1);
        assertTrue(addUserResponse.hasErrors());
        assertEquals(addUserResponse.getErrorList().get(0).getField(), "User first name");
        assertEquals(addUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

}
