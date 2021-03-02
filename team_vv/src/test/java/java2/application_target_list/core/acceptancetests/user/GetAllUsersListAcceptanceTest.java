package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.GetAllUserService;;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class GetAllUsersListAcceptanceTest {

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
    public void shouldReturnUsersList() {
        AddUserRequest addUserRequest = createAddUserRequest("name", "surname");
        AddUserResponse addUserResponse = createAddUserResponse(addUserRequest);

        GetAllUsersRequest getAllUsersRequest = createGetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = createGetAllUserResponse(getAllUsersRequest);

        Assertions.assertFalse(addUserResponse.hasErrors());
        Assertions.assertEquals(getAllUsersResponse.getUsersList().size(), 1);
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        Assertions.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
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
