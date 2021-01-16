package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.requests.user.*;
import java2.application_target_list.core.responses.user.*;
import java2.application_target_list.core.services.board.GetAllRecordsService;
import java2.application_target_list.core.services.user.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AcceptanceTest {

    private AddUserService addUserService;
    private DeleteUserService deleteUserService;
    private GetAllUserService getAllUsersService;
    private ChangeUserFirstNameService changeUserFirstNameService;
    private ChangeUserLastNameService changeUserLastNameService;
    private AddUserResponse addUserResponse;
    private SearchUserByFirstNameService searchUserByFirstNameService;
    private SearchUserByLastNameService searchUserByLastNameService;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        addUserService = applicationContext.getBean(AddUserService.class);
        deleteUserService = applicationContext.getBean(DeleteUserService.class);
        getAllUsersService = applicationContext.getBean(GetAllUserService.class);
        changeUserFirstNameService = applicationContext.getBean(ChangeUserFirstNameService.class);
        changeUserLastNameService = applicationContext.getBean(ChangeUserLastNameService.class);
        searchUserByFirstNameService = applicationContext.getBean(SearchUserByFirstNameService.class);
        searchUserByLastNameService = applicationContext.getBean(SearchUserByLastNameService.class);
    }

    @Test
    public void acceptanceTest() {
        AddUserRequest addUserRequest1 = new AddUserRequest("name1", "surname1"); //
        AddUserRequest addUserRequest2 = new AddUserRequest("name2", "surname2");
        AddUserRequest addUserRequest3 = new AddUserRequest("name3", "surname3");
        addUserResponse = addUserService.execute(addUserRequest1);
        Assert.assertFalse(addUserResponse.hasErrors());
        addUserResponse = addUserService.execute(addUserRequest2);
        Assert.assertFalse(addUserResponse.hasErrors());
        addUserResponse = addUserService.execute(addUserRequest3);
        Assert.assertFalse(addUserResponse.hasErrors());

        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(1L);
        DeleteUserResponse deleteUserResponse = deleteUserService.execute(deleteUserRequest);
        Assert.assertFalse(deleteUserResponse.hasErrors());

        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(2L, "New Name");
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assert.assertFalse(changeUserFirstNameResponse.hasErrors());

        ChangeUserLastNameRequest changeUserLastNameRequest = new ChangeUserLastNameRequest(3L, "New Surname");
        ChangeUserLastNameResponse changeUserLastNameResponse = changeUserLastNameService.execute(changeUserLastNameRequest);
        Assert.assertFalse(changeUserLastNameResponse.hasErrors());

        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = getAllUsersService.execute(getAllUsersRequest);
        Assert.assertFalse(changeUserLastNameResponse.hasErrors());
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "New Name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname2");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name3");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "New Surname");

        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("New Name");
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assert.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 1);
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "New Name");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname2");

        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("New Surname");
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        Assert.assertFalse(searchUserByLastNameResponse.hasErrors());
        Assert.assertEquals(searchUserByLastNameResponse.getUsersList().size(), 1);
        Assert.assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name3");
        Assert.assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "New Surname");
    }
}
