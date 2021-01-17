package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.ChangeUserLastNameResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.ChangeUserLastNameService;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ChangeUserLastNameAcceptanceTest {


    private ApplicationContext applicationContext;
    private AddUserService addUserService;
    private GetAllUserService getAllUserService;
    private ChangeUserLastNameService changeUserLastNameService;


    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        addUserService = applicationContext.getBean(AddUserService.class);
        getAllUserService = applicationContext.getBean(GetAllUserService.class);
        changeUserLastNameService = applicationContext.getBean(ChangeUserLastNameService.class);

        AddUserRequest addUserRequest1 = new AddUserRequest("name", "surname");
        AddUserRequest addUserRequest2 = new AddUserRequest("name2", "surname2");
        addUserService.execute(addUserRequest1);
        addUserService.execute(addUserRequest2);
    }

    @Test
    public void shouldChangeUserFirstName() {
        changeUserLastNameService.execute(new ChangeUserLastNameRequest(2L, "New Last Name"));
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "New Last Name");
    }

    @Test
    public void shouldReturnErrorList() {
        ChangeUserLastNameResponse changeUserLastNameResponse = changeUserLastNameService.execute(
                new ChangeUserLastNameRequest(2L, ""));
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "surname2");
        Assert.assertTrue(changeUserLastNameResponse.hasErrors());
        Assert.assertEquals(changeUserLastNameResponse.getErrorList().size(), 1);
        Assert.assertEquals(changeUserLastNameResponse.getErrorList().get(0).getField(), "User new last name");
        Assert.assertEquals(changeUserLastNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

}
