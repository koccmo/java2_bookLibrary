package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.database.user.UserDatabase;
import java2.application_target_list.core.database.user.UserListImpl;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ChangeUserFirstNameValidatorTest {

    private ChangeUserFirstNameValidator changeUserFirstNameValidator;
    private UserDatabase userDatabase;

    @Before
    public void setup() {
        changeUserFirstNameValidator = new ChangeUserFirstNameValidator();
        userDatabase = new UserListImpl();
        userDatabase.addUser(new User("name", "surname"));
    }

    @Test
    public void testValidate_validRequest() {
        ChangeUserFirstNameRequest request = new ChangeUserFirstNameRequest(1L, "new first name");
        List<CoreError> actualErrors = changeUserFirstNameValidator.validate(request, userDatabase);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequest_v1() {
        ChangeUserFirstNameRequest request = new ChangeUserFirstNameRequest(-1L, "new name");
        List<CoreError> actualErrors = changeUserFirstNameValidator.validate(request, userDatabase);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no user with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("User ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        ChangeUserFirstNameRequest request = new ChangeUserFirstNameRequest(3L, "new name");
        List<CoreError> actualErrors = changeUserFirstNameValidator.validate(request, userDatabase);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no user with that ID"));
    }

    @Test
    public void testValidate_invalidRequest_v3() {
        ChangeUserFirstNameRequest request = new ChangeUserFirstNameRequest(1L, "");
        List<CoreError> actualErrors = changeUserFirstNameValidator.validate(request, userDatabase);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User new first name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v4() {
        ChangeUserFirstNameRequest request = new ChangeUserFirstNameRequest(1L, null);
        List<CoreError> actualErrors = changeUserFirstNameValidator.validate(request, userDatabase);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User new first name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v5() {
        ChangeUserFirstNameRequest request = new ChangeUserFirstNameRequest(-1L, null);
        List<CoreError> actualErrors = changeUserFirstNameValidator.validate(request, userDatabase);
        Assert.assertEquals(actualErrors.size(), 3);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no user with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("User ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("User new first name"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must not be empty!"));
    }
}