package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.database.UserDatabase;
import java2.application_target_list.core.database.UserListImpl;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.responses.CoreError;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DeleteUserValidatorTest {

    private DeleteUserValidator deleteUserValidator;
    private UserDatabase userDatabase;

    @Before
    public void setup() {
        deleteUserValidator = new DeleteUserValidator();
        userDatabase = new UserListImpl();
        userDatabase.addUser(new User("name", "surname"));
    }

    @Test
    public void testValidate_validRequest() {
        DeleteUserRequest request = new DeleteUserRequest(1L);
        List<CoreError> actualErrors = deleteUserValidator.validate(request, userDatabase);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequest_v1() {
        DeleteUserRequest request = new DeleteUserRequest(2L);
        List<CoreError> actualErrors = deleteUserValidator.validate(request, userDatabase);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no user with that ID"));
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        DeleteUserRequest request = new DeleteUserRequest(-2L);
        List<CoreError> actualErrors = deleteUserValidator.validate(request, userDatabase);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no user with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("User ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
    }
}