package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.ChangeUserFirstNameResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.ChangeUserFirstNameService;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ChangeUserFirstNameAcceptanceTest {

    private ApplicationContext applicationContext;
    private AddUserService addUserService;
    private GetAllUserService getAllUserService;
    private ChangeUserFirstNameService changeUserFirstNameService;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        addUserService = applicationContext.getBean(AddUserService.class);
        getAllUserService = applicationContext.getBean(GetAllUserService.class);
        changeUserFirstNameService = applicationContext.getBean(ChangeUserFirstNameService.class);

        AddUserRequest addUserRequest1 = new AddUserRequest("name", "surname");
        AddUserRequest addUserRequest2 = new AddUserRequest("name2", "surname2");
        addUserService.execute(addUserRequest1);
        addUserService.execute(addUserRequest2);
    }

    @Test
    public void shouldChangeUserFirstName() {
        changeUserFirstNameService.execute(new ChangeUserFirstNameRequest(1L, "New Name"));
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assert.assertFalse(getAllUsersResponse.hasErrors());
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "New Name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "surname2");
    }

    @Test
    public void shouldReturnErrorList() {
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(
                new ChangeUserFirstNameRequest(3L, "New Name"));
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());
        Assert.assertTrue(changeUserFirstNameResponse.hasErrors());
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().size(), 1);
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User ID;");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "no user with that ID");
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assert.assertFalse(getAllUsersResponse.hasErrors());
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "surname2");
    }
}
