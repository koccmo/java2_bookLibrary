package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.responses.user.DeleteUserResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.DeleteUserService;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TargetListApplication.class})
public class DeleteUserAcceptanceTest {

    @Autowired
    private AddUserService addUserService;
    @Autowired
    private GetAllUserService getAllUserService;
    @Autowired
    private DeleteUserService deleteUserService;

    private Long idToDelete;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Before
    public void setup() {
        databaseCleaner.clean();
        addUsersToDatabase();
    }

    @Test
    public void shouldDeleteUsersFromDB_v1() {
        GetAllUsersRequest getAllUsersRequest = createGetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponseBeforeDelete = createGetAllUserResponse(getAllUsersRequest);

        idToDelete = getAllUsersResponseBeforeDelete.getUsersList().get(0).getId();

        DeleteUserRequest deleteUserRequest = createDeleteUserRequest(idToDelete);
        DeleteUserResponse deleteUserResponse = createDeleteUserResponse(deleteUserRequest);

        GetAllUsersResponse getAllUsersResponseAfterDelete = createGetAllUserResponse(getAllUsersRequest);

        assertFalse(deleteUserResponse.hasErrors());
        assertEquals(getAllUsersResponseAfterDelete.getUsersList().size(), 1);
        assertNull(getAllUsersResponseAfterDelete.getErrorList());
        assertEquals(getAllUsersResponseAfterDelete.getUsersList().get(0).getFirstName(), "name2");
        assertEquals(getAllUsersResponseAfterDelete.getUsersList().get(0).getLastName(), "surname2");
    }

    @Test
    public void shouldDeleteUsersFromDB_v2() {
        GetAllUsersRequest getAllUsersRequest = createGetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponseBeforeDelete = createGetAllUserResponse(getAllUsersRequest);

        idToDelete = getAllUsersResponseBeforeDelete.getUsersList().get(1).getId();

        DeleteUserRequest deleteUserRequest = createDeleteUserRequest(idToDelete);
        DeleteUserResponse deleteUserResponse = createDeleteUserResponse(deleteUserRequest);

        GetAllUsersResponse getAllUsersResponseAfterDelete = createGetAllUserResponse(getAllUsersRequest);

        assertFalse(deleteUserResponse.hasErrors());
        assertEquals(getAllUsersResponseAfterDelete.getUsersList().size(), 1);
        assertNull(getAllUsersResponseAfterDelete.getErrorList());
        assertEquals(getAllUsersResponseAfterDelete.getUsersList().get(0).getFirstName(), "name");
        assertEquals(getAllUsersResponseAfterDelete.getUsersList().get(0).getLastName(), "surname");
    }

    @Test
    public void shouldDeleteUsersFromDB_v3() {
        GetAllUsersRequest getAllUsersRequest = createGetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponseBeforeDelete = createGetAllUserResponse(getAllUsersRequest);

        idToDelete = getAllUsersResponseBeforeDelete.getUsersList().get(1).getId();
        DeleteUserRequest deleteUserRequest1 = createDeleteUserRequest(idToDelete);
        DeleteUserResponse deleteUserResponse1 = createDeleteUserResponse(deleteUserRequest1);

        idToDelete = getAllUsersResponseBeforeDelete.getUsersList().get(0).getId();
        DeleteUserRequest deleteUserRequest2 = createDeleteUserRequest(idToDelete);
        DeleteUserResponse deleteUserResponse2 = createDeleteUserResponse(deleteUserRequest2);

        GetAllUsersResponse getAllUsersResponseAfterDelete = createGetAllUserResponse(getAllUsersRequest);

        assertFalse(deleteUserResponse1.hasErrors());
        assertFalse(deleteUserResponse2.hasErrors());
        assertEquals(getAllUsersResponseAfterDelete.getUsersList().size(), 0);
        assertNull(getAllUsersResponseAfterDelete.getErrorList());
    }

    @Test
    public void shouldReturnErrorList() {
        DeleteUserRequest deleteUserRequest = createDeleteUserRequest(3L);
        DeleteUserResponse deleteUserResponse = createDeleteUserResponse(deleteUserRequest);

        assertFalse(deleteUserResponse.getErrorList().isEmpty());
        assertEquals(deleteUserResponse.getErrorList().size(), 1);
        assertEquals(deleteUserResponse.getErrorList().get(0).getField(), "User ID;");
        assertEquals(deleteUserResponse.getErrorList().get(0).getMessage(), "no user with that ID");
    }

    private void addUsersToDatabase() {
        AddUserRequest addUserRequest1 = createAddUserRequest("name", "surname");
        AddUserRequest addUserRequest2 = createAddUserRequest("name2", "surname2");
        AddUserResponse addUserResponse1 = createAddUserResponse(addUserRequest1);
        AddUserResponse addUserResponse2 = createAddUserResponse(addUserRequest2);
    }

    private DeleteUserResponse createDeleteUserResponse(DeleteUserRequest deleteUserRequest) {
        return deleteUserService.execute(deleteUserRequest);
    }

    private DeleteUserRequest createDeleteUserRequest(Long idToDelete) {
        return new DeleteUserRequest(idToDelete);
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
