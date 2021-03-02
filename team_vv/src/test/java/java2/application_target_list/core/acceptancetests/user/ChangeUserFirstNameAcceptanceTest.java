package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.responses.user.ChangeUserFirstNameResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.ChangeUserFirstNameService;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@SpringBootTest
public class ChangeUserFirstNameAcceptanceTest {

    @Autowired
    private AddUserService addUserService;
    @Autowired
    private GetAllUserService getAllUserService;
    @Autowired
    private ChangeUserFirstNameService changeUserFirstNameService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    private Long userId;
    private List<User> userList;



    @BeforeEach
    public void setup() {
        databaseCleaner.clean();
        addUsersToDatabase();
        userList = getAllUserService.execute(new GetAllUsersRequest()).getUsersList();
    }

    @Test
    public void shouldChangeUserFirstName() {
        userId = userList.get(0).getId();
        ChangeUserFirstNameResponse changeUserFirstNameResponse =
                changeUserFirstNameService.execute(new ChangeUserFirstNameRequest(userId, "New Name"));

        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());

        Assertions.assertFalse(changeUserFirstNameResponse.hasErrors());
        Assertions.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assertions.assertFalse(getAllUsersResponse.hasErrors());
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "New Name");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name2");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "surname2");
    }

    @Test
    public void shouldReturnErrorList() {
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(
                new ChangeUserFirstNameRequest(3L, "New Name"));

        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());

        Assertions.assertTrue(changeUserFirstNameResponse.hasErrors());
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().size(), 1);
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User ID;");
        Assertions.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "no user with that ID");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assertions.assertFalse(getAllUsersResponse.hasErrors());
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name2");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "surname2");
    }


    private AddUserResponse createAddUserResponse(AddUserRequest addUserRequest) {
        return addUserService.execute(addUserRequest);
    }

    private AddUserRequest createAddUserRequest(String userFirstName, String userLastName) {
        return new AddUserRequest(userFirstName, userLastName);
    }

    private void addUsersToDatabase() {
        AddUserRequest addUserRequest1 = createAddUserRequest("name", "surname");
        AddUserRequest addUserRequest2 = createAddUserRequest("name2", "surname2");
        AddUserResponse addUserResponse1 = createAddUserResponse(addUserRequest1);
        AddUserResponse addUserResponse2 = createAddUserResponse(addUserRequest2);
    }
}
