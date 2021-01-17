package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.user.UserDatabase;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.user.SearchUsersByLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.SearchUserByLastNameResponse;
import java2.application_target_list.core.validators.user.SearchUserByLastNameValidator;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SearchUserByLastNameServiceTest extends TestCase {

    private List<CoreError> errors;
    private List<User> users;
    @Mock private UserDatabase userDatabase;
    @Mock private SearchUserByLastNameValidator searchUserByLastNameValidator;
    @InjectMocks private SearchUserByLastNameService searchUserByLastNameService;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(searchUserByLastNameService, "orderingEnabled", true);
        ReflectionTestUtils.setField(searchUserByLastNameService, "pagingEnabled", true);
        errors = new ArrayList<>();
        users = new ArrayList<>();
    }

    @Test
    public void shouldFindUserByLastName() {
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname");
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name", "surname"));
        Mockito.when(userDatabase.findUserByLastName("surname")).thenReturn(users);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname");
    }

    @Test
    public void shouldFindUserByLastNameWithOrderingByFirstNameASC() {
        Ordering ordering = new Ordering("first name", "ASCENDING");
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname", ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name2", "surname2"));
        users.add(new User("name1", "surname1"));
        Mockito.when(userDatabase.findUserByLastName("surname")).thenReturn(users);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 2);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getFirstName(), "name2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getLastName(), "surname2");
    }

    @Test
    public void shouldFindUserByLastNameWithOrderingByFirstNameDSC() {
        Ordering ordering = new Ordering("first name", "DESCENDING");
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname", ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name1", "surname1"));
        users.add(new User("name2", "surname2"));
        Mockito.when(userDatabase.findUserByLastName("surname")).thenReturn(users);
        SearchUserByLastNameResponse searchUserByFirstNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertFalse(searchUserByFirstNameResponse.hasErrors());
        assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 2);
        assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name2");
        assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname2");
        assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getFirstName(), "name1");
        assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getLastName(), "surname1");
    }

    @Test
    public void shouldFindUserByLastNameWithOrderingByLastNameDSC() {
        Ordering ordering = new Ordering("last name", "DESCENDING");
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname", ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name1", "surname1"));
        users.add(new User("name2", "surname2"));
        Mockito.when(userDatabase.findUserByLastName("surname")).thenReturn(users);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 2);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getFirstName(), "name1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getLastName(), "surname1");
    }

    @Test
    public void shouldFindUserByLastNameWithOrderingByLastNameASC() {
        Ordering ordering = new Ordering("last name", "ASCENDING");
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname", ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name2", "surname2"));
        users.add(new User("name1", "surname1"));
        Mockito.when(userDatabase.findUserByLastName("surname")).thenReturn(users);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 2);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getFirstName(), "name2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getLastName(), "surname2");
    }

    @Test
    public void shouldFindUserByLastNameWithPaging() {
        Paging paging = new Paging(1,1);
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname", paging);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name1", "surname1"));
        users.add(new User("name2", "surname2"));
        Mockito.when(userDatabase.findUserByLastName("surname")).thenReturn(users);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname1");
    }

    @Test
    public void shouldFindUserByLastNameWithPagingAndOrderingByFirstNameASC() {
        Paging paging = new Paging(1,1);
        Ordering ordering = new Ordering("first name", "ASCENDING");
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname", paging, ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name2", "surname2"));
        users.add(new User("name1", "surname1"));
        Mockito.when(userDatabase.findUserByLastName("surname")).thenReturn(users);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname1");
    }

    @Test
    public void shouldFindUserByLastNameWithPagingAndOrderingByFirstNameDSC() {
        Paging paging = new Paging(1,1);
        Ordering ordering = new Ordering("first name", "DESCENDING");
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname", paging, ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name1", "surname1"));
        users.add(new User("name2", "surname2"));
        Mockito.when(userDatabase.findUserByLastName("surname")).thenReturn(users);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname2");
    }

    @Test
    public void shouldFindUserByLastNameWithPagingAndOrderingByLastNameDSC() {
        Paging paging = new Paging(1,1);
        Ordering ordering = new Ordering("last name", "DESCENDING");
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname", paging, ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name1", "surname1"));
        users.add(new User("name2", "surname2"));
        Mockito.when(userDatabase.findUserByLastName("surname")).thenReturn(users);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname2");
    }

    @Test
    public void shouldFindUserByLastNameWithPagingAndOrderingByLastNameASC() {
        Paging paging = new Paging(1,1);
        Ordering ordering = new Ordering("last name", "ASCENDING");
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname", paging, ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name2", "surname2"));
        users.add(new User("name1", "surname1"));
        Mockito.when(userDatabase.findUserByLastName("surname")).thenReturn(users);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname1");
    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("");
        errors.add(new CoreError("User last name", "must not be empty!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(errors);
        SearchUserByLastNameResponse searchUserByFirstNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertTrue(searchUserByFirstNameResponse.hasErrors());
        assertEquals(searchUserByFirstNameResponse.getErrorList().size(), 1);
        assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getField(), "User last name");
        assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest(null);
        errors.add(new CoreError("User last name", "must not be empty!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(errors);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertTrue(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getErrorList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getField(), "User last name");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname", ordering);
        errors.add(new CoreError("Order by", "must not be empty"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(errors);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertTrue(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getErrorList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getField(), "Order by");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void shouldReturnResponseWithErrors_v4() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname", ordering);
        errors.add(new CoreError("Order by", "must contain FIRST NAME or LAST NAME only!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(errors);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertTrue(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getErrorList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getField(), "Order by");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getMessage(), "must contain FIRST NAME or LAST NAME only!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v5() {
        Ordering ordering = new Ordering("name", "");
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("name", ordering);
        errors.add(new CoreError("Order direction", "must not be empty"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(errors);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertTrue(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getErrorList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getField(), "Order direction");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void shouldReturnResponseWithErrors_v6() {
        Ordering ordering = new Ordering("name", "asc");
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("name", ordering);
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(errors);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertTrue(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getErrorList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getField(), "Order direction");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getMessage(), "must contain ASCENDING or DESCENDING only!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v7() {
        Ordering ordering = new Ordering("", "asc");
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("name", ordering);
        errors.add(new CoreError("User first name", "must not be empty!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(errors);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertTrue(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getErrorList().size(), 2);
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getField(), "User first name");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(1).getField(), "Order direction");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(1).getMessage(), "must contain ASCENDING or DESCENDING only!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v8() {
        Paging paging = new Paging(-1,1);
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("name", paging);
        errors.add(new CoreError("Page number", "must be greater then 0!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(errors);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertTrue(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getErrorList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getField(), "Page number");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getMessage(), "must be greater then 0!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v9() {
        Paging paging = new Paging(null,1);
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("name", paging);
        errors.add(new CoreError("Page number", "must not be empty"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(errors);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertTrue(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getErrorList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getField(), "Page number");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void shouldReturnResponseWithErrors_v10() {
        Paging paging = new Paging(1,-1);
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("name", paging);
        errors.add(new CoreError("Page size", "must be greater then 0!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(errors);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertTrue(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getErrorList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getField(), "Page size");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getMessage(), "must be greater then 0!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v11() {
        Paging paging = new Paging(1,null);
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("name", paging);
        errors.add(new CoreError("Page size", "must not be empty"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(errors);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertTrue(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getErrorList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getField(), "Page size");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void shouldReturnResponseWithErrors_v12() {
        Paging paging = new Paging(-1,null);
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("name", paging);
        errors.add(new CoreError("Page number", "must be greater then 0!"));
        errors.add(new CoreError("Page size", "must not be empty"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(errors);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertTrue(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getErrorList().size(), 2);
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getField(), "Page number");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getMessage(), "must be greater then 0!");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(1).getField(), "Page size");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(1).getMessage(), "must not be empty");
    }

    @Test
    public void shouldReturnResponseWithErrors_v13() {
        Paging paging = new Paging(-1,null);
        Ordering ordering = new Ordering("name", "dsc");
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest(null, paging, ordering);
        errors.add(new CoreError("User last name", "must not be empty!"));
        errors.add(new CoreError("Page number", "must be greater then 0!"));
        errors.add(new CoreError("Page size", "must not be empty"));
        errors.add(new CoreError("Order by", "must not be empty"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByLastNameRequest)).thenReturn(errors);
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        assertTrue(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getErrorList().size(), 5);
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getField(), "User last name");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(3).getField(), "Order by");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(3).getMessage(), "must not be empty");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(4).getField(), "Order direction");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(4).getMessage(), "must contain ASCENDING or DESCENDING only!");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(1).getField(), "Page number");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(1).getMessage(), "must be greater then 0!");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(2).getField(), "Page size");
        assertEquals(searchUserByLastNameResponse.getErrorList().get(2).getMessage(), "must not be empty");
    }
}