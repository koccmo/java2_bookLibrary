package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.config.SpringCoreConfiguration;
import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.user.*;
import java2.application_target_list.core.responses.user.*;
import java2.application_target_list.core.services.user.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AcceptanceTest {

    private ApplicationContext applicationContext;
    private AddUserService addUserService;
    private DeleteUserService deleteUserService;
    private GetAllUserService getAllUsersService;
    private ChangeUserFirstNameService changeUserFirstNameService;
    private ChangeUserLastNameService changeUserLastNameService;
    private SearchUserByFirstNameService searchUserByFirstNameService;
    private SearchUserByLastNameService searchUserByLastNameService;
    private DatabaseCleaner databaseCleaner;
    private Long userId;

    @Before
    public void setup() {
        createServices();
        databaseCleaner.clean();
    }

    @Test
    public void acceptanceTest() {
        addUsersToDB();
        deleteFirstUser();
        changeSecondUserFirstName();
        changeThirdUserLastName();
        checkChanges();
        searchUsersByFirstName();
        searchUsersByLastName();
    }

    private void searchUsersByLastName() {
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("New Surname");
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        Assert.assertFalse(searchUserByLastNameResponse.hasErrors());
        Assert.assertEquals(searchUserByLastNameResponse.getUsersList().size(), 1);
        Assert.assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name3");
        Assert.assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "New Surname");
    }

    private void searchUsersByFirstName() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("New Name");
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assert.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 1);
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "New Name");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname2");
    }

    private void checkChanges() {
        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = getAllUsersService.execute(getAllUsersRequest);
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "New Name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname2");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name3");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "New Surname");
    }

    private void changeThirdUserLastName() {
        userId = getAllUsersService.execute(new GetAllUsersRequest()).getUsersList().get(1).getId();
        ChangeUserLastNameRequest changeUserLastNameRequest = new ChangeUserLastNameRequest(userId, "New Surname");
        ChangeUserLastNameResponse changeUserLastNameResponse = changeUserLastNameService.execute(changeUserLastNameRequest);
        Assert.assertFalse(changeUserLastNameResponse.hasErrors());
    }

    private void changeSecondUserFirstName() {
        userId = getAllUsersService.execute(new GetAllUsersRequest()).getUsersList().get(0).getId();
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(userId, "New Name");
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assert.assertFalse(changeUserFirstNameResponse.hasErrors());

        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = getAllUsersService.execute(getAllUsersRequest);
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "New Name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname2");
    }

    private void deleteFirstUser() {
        userId = getAllUsersService.execute(new GetAllUsersRequest()).getUsersList().get(0).getId();
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(userId);
        DeleteUserResponse deleteUserResponse = deleteUserService.execute(deleteUserRequest);
        Assert.assertFalse(deleteUserResponse.hasErrors());
    }

    private void addUsersToDB() {
        AddUserRequest addUserRequest1 = new AddUserRequest("name1", "surname1");
        AddUserRequest addUserRequest2 = new AddUserRequest("name2", "surname2");
        AddUserRequest addUserRequest3 = new AddUserRequest("name3", "surname3");

        AddUserResponse addUserResponse1 = addUserService.execute(addUserRequest1);
        AddUserResponse addUserResponse2 = addUserService.execute(addUserRequest2);
        AddUserResponse addUserResponse3 = addUserService.execute(addUserRequest3);

        Assert.assertFalse(addUserResponse1.hasErrors());
        Assert.assertFalse(addUserResponse2.hasErrors());
        Assert.assertFalse(addUserResponse3.hasErrors());
    }

    private ApplicationContext createApplicationContext(){
        return new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
    }

    private DatabaseCleaner createDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }

    private AddUserService createAddUserService() {
        return applicationContext.getBean(AddUserService.class);
    }

    private SearchUserByLastNameService createSearchUserByLastNameService() {
        return applicationContext.getBean(SearchUserByLastNameService.class);
    }

    private DeleteUserService createDeleteUserService() {
        return applicationContext.getBean(DeleteUserService.class);
    }

    private GetAllUserService createGetAllUserService() {
        return applicationContext.getBean(GetAllUserService.class);
    }

    private SearchUserByFirstNameService createSearchUserByFirstNameService() {
        return applicationContext.getBean(SearchUserByFirstNameService.class);
    }

    private ChangeUserFirstNameService createChangeUserFirstNameService() {
        return applicationContext.getBean(ChangeUserFirstNameService.class);
    }

    private ChangeUserLastNameService createChangeUserLastNameService() {
        return applicationContext.getBean(ChangeUserLastNameService.class);
    }

    private void createServices() {
        applicationContext = createApplicationContext();
        addUserService = createAddUserService();
        deleteUserService = createDeleteUserService();
        getAllUsersService = createGetAllUserService();
        changeUserFirstNameService = createChangeUserFirstNameService();
        changeUserLastNameService = createChangeUserLastNameService();
        searchUserByFirstNameService = createSearchUserByFirstNameService();
        searchUserByLastNameService = createSearchUserByLastNameService();
        databaseCleaner = createDatabaseCleaner();
    }
}
