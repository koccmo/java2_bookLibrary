package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class AddUserAcceptanceTests {

    @Autowired
    private AddUserService addUserService;
    @Autowired
    private GetAllUserService getAllUserService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void shouldAddUsersToDB() {
        AddUserRequest addUserRequest1 = createAddUserRequest("name", "surname");
        AddUserRequest addUserRequest2 = createAddUserRequest("name2", "surname2");

        AddUserResponse addUserResponse1 = createAddUserResponse(addUserRequest1);
        AddUserResponse addUserResponse2 = createAddUserResponse(addUserRequest2);

        GetAllUsersRequest getAllUsersRequest = createGetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = createGetAllUserResponse(getAllUsersRequest);

        Assertions.assertFalse(addUserResponse1.hasErrors());
        Assertions.assertFalse(addUserResponse2.hasErrors());
        Assertions.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assertions.assertNull(getAllUsersResponse.getErrorList());
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name2");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "surname2");
    }

    @Test
    public void shouldReturnErrorList() {
        AddUserRequest addUserRequest = createAddUserRequest("", "surname2");
        AddUserResponse addUserResponse = createAddUserResponse(addUserRequest);

        Assertions.assertEquals(addUserResponse.getErrorList().size(), 1);
        Assertions.assertTrue(addUserResponse.hasErrors());
        Assertions.assertEquals(addUserResponse.getErrorList().get(0).getField(), "User first name");
        Assertions.assertEquals(addUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    private AddUserResponse createAddUserResponse(AddUserRequest addUserRequest) {
        return addUserService.execute(addUserRequest);
    }

    private GetAllUsersResponse createGetAllUserResponse(GetAllUsersRequest getAllUsersRequest) {
        return getAllUserService.execute(getAllUsersRequest);
    }

    private GetAllUsersRequest createGetAllUsersRequest() {
        return new GetAllUsersRequest();
    }

    private AddUserRequest createAddUserRequest(String userFirstName, String userLastName) {
        return new AddUserRequest(userFirstName, userLastName);
    }
}
