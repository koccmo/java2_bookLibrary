package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.AddUserResponse;
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

import java.util.List;

public class ChangeUserLastNameAcceptanceTest {


    private GetAllUserService getAllUserService;
    private ChangeUserLastNameService changeUserLastNameService;
    private AddUserService addUserService;
    private ApplicationContext applicationContext;
    private Long userId;
    private List<User> userList;
    private DatabaseCleaner databaseCleaner;


    @Before
    public void setup() {
        createServices();
        databaseCleaner.clean();
        addUsersToDatabase();
        userList = getAllUserService.execute(new GetAllUsersRequest()).getUsersList();
    }

    @Test
    public void shouldChangeUserFirstName() {
        userId = userList.get(1).getId();

        ChangeUserLastNameResponse changeUserLastNameResponse =
                changeUserLastNameService.execute(new ChangeUserLastNameRequest(userId, "New Last Name"));
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());

        Assert.assertFalse(changeUserLastNameResponse.hasErrors());
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "New Last Name");
    }

    @Test
    public void shouldReturnErrorList() {
        userId = userList.get(1).getId();

        ChangeUserLastNameResponse changeUserLastNameResponse = changeUserLastNameService.execute(
                new ChangeUserLastNameRequest(userId, ""));
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

    private AddUserResponse createAddUserResponse(AddUserRequest addUserRequest) {
        return addUserService.execute(addUserRequest);
    }

    private AddUserRequest createAddUserRequest(String userFirstName, String userLastName) {
        return new AddUserRequest(userFirstName, userLastName);
    }

    private ApplicationContext createApplicationContext(){
        return new AnnotationConfigApplicationContext(TargetListConfiguration.class);
    }

    private DatabaseCleaner createDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }

    private GetAllUserService createGetAllUserService() {
        return applicationContext.getBean(GetAllUserService.class);
    }

    private AddUserService createAddUserService() {
        return applicationContext.getBean(AddUserService.class);
    }

    private ChangeUserLastNameService createChangeUserLastNameService() {
        return applicationContext.getBean(ChangeUserLastNameService.class);
    }

    private void addUsersToDatabase() {
        AddUserRequest addUserRequest1 = createAddUserRequest("name", "surname");
        AddUserRequest addUserRequest2 = createAddUserRequest("name2", "surname2");
        AddUserResponse addUserResponse1 = createAddUserResponse(addUserRequest1);
        AddUserResponse addUserResponse2 = createAddUserResponse(addUserRequest2);
    }

    private void createServices() {
        applicationContext = createApplicationContext();
        addUserService = createAddUserService();
        getAllUserService = createGetAllUserService();
        changeUserLastNameService = createChangeUserLastNameService();
        databaseCleaner = createDatabaseCleaner();
    }

}
