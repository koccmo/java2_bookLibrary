package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.user.*;
import java2.application_target_list.core.responses.user.*;
import java2.application_target_list.core.services.user.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class AcceptanceTest {

    @Autowired
    private AddUserService addUserService;
    @Autowired
    private DeleteUserService deleteUserService;
    @Autowired
    private GetAllUserService getAllUsersService;
    @Autowired
    private ChangeUserFirstNameService changeUserFirstNameService;
    @Autowired
    private ChangeUserLastNameService changeUserLastNameService;
    @Autowired
    private SearchUserByFirstNameService searchUserByFirstNameService;
    @Autowired
    private SearchUserByLastNameService searchUserByLastNameService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    private Long userId;

    @BeforeEach
    public void setup() {
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
        Assertions.assertFalse(searchUserByLastNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByLastNameResponse.getUsersList().size(), 1);
        Assertions.assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name3");
        Assertions.assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "New Surname");
    }

    private void searchUsersByFirstName() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("New Name");
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "New Name");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname2");
    }

    private void checkChanges() {
        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = getAllUsersService.execute(getAllUsersRequest);
        Assertions.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "New Name");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname2");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name3");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "New Surname");
    }

    private void changeThirdUserLastName() {
        userId = getAllUsersService.execute(new GetAllUsersRequest()).getUsersList().get(1).getId();
        ChangeUserLastNameRequest changeUserLastNameRequest = new ChangeUserLastNameRequest(userId, "New Surname");
        ChangeUserLastNameResponse changeUserLastNameResponse = changeUserLastNameService.execute(changeUserLastNameRequest);
        Assertions.assertFalse(changeUserLastNameResponse.hasErrors());
    }

    private void changeSecondUserFirstName() {
        userId = getAllUsersService.execute(new GetAllUsersRequest()).getUsersList().get(0).getId();
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(userId, "New Name");
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assertions.assertFalse(changeUserFirstNameResponse.hasErrors());

        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = getAllUsersService.execute(getAllUsersRequest);
        Assertions.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "New Name");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname2");
    }

    private void deleteFirstUser() {
        userId = getAllUsersService.execute(new GetAllUsersRequest()).getUsersList().get(0).getId();
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(userId);
        DeleteUserResponse deleteUserResponse = deleteUserService.execute(deleteUserRequest);
        Assertions.assertFalse(deleteUserResponse.hasErrors());
    }

    private void addUsersToDB() {
        AddUserRequest addUserRequest1 = new AddUserRequest("name1", "surname1");
        AddUserRequest addUserRequest2 = new AddUserRequest("name2", "surname2");
        AddUserRequest addUserRequest3 = new AddUserRequest("name3", "surname3");

        AddUserResponse addUserResponse1 = addUserService.execute(addUserRequest1);
        AddUserResponse addUserResponse2 = addUserService.execute(addUserRequest2);
        AddUserResponse addUserResponse3 = addUserService.execute(addUserRequest3);

        Assertions.assertFalse(addUserResponse1.hasErrors());
        Assertions.assertFalse(addUserResponse2.hasErrors());
        Assertions.assertFalse(addUserResponse3.hasErrors());
    }
}
