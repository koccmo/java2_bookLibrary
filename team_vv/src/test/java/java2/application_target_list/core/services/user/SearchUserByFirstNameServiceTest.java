package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.user.SearchUsersByFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.SearchUserByFirstNameResponse;
import java2.application_target_list.core.validators.user.SearchUserByFirstNameValidator;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.ArrayList;


@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class SearchUserByFirstNameServiceTest {

    private List<CoreError> errors;
    private List<User> users;
    @Mock
    private JpaUserRepository jpaUserRepository;
    @Mock
    private SearchUserByFirstNameValidator searchUserByLastNameValidator;
    @InjectMocks
    SearchUserByFirstNameService searchUserByFirstNameService;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(searchUserByFirstNameService, "orderingEnabled", true);
        ReflectionTestUtils.setField(searchUserByFirstNameService, "pagingEnabled", true);
        errors = new ArrayList<>();
        users = new ArrayList<>();
    }

    @Test
    public void shouldFindUserByFirstName() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name");
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name", "surname"));
        Mockito.when(jpaUserRepository.findByFirstName("name")).thenReturn(users);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname");
    }

    @Test
    public void shouldFindUserByFirstNameWithOrderingByFirstNameASC() {
        Ordering ordering = new Ordering("first name", "ASCENDING");
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name2", "surname2"));
        users.add(new User("name1", "surname1"));
        Mockito.when(jpaUserRepository.findByFirstName("name")).thenReturn(users);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 2);
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name1");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname1");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getFirstName(), "name2");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getLastName(), "surname2");
    }

    @Test
    public void shouldFindUserByFirstNameWithOrderingByFirstNameDSC() {
        Ordering ordering = new Ordering("first name", "DESCENDING");
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name1", "surname1"));
        users.add(new User("name2", "surname2"));
        Mockito.when(jpaUserRepository.findByFirstName("name")).thenReturn(users);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 2);
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name2");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname2");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getFirstName(), "name1");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getLastName(), "surname1");
    }

    @Test
    public void shouldFindUserByFirstNameWithOrderingByLastNameDSC() {
        Ordering ordering = new Ordering("last name", "DESCENDING");
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name1", "surname1"));
        users.add(new User("name2", "surname2"));
        Mockito.when(jpaUserRepository.findByFirstName("name")).thenReturn(users);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 2);
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name2");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname2");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getFirstName(), "name1");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getLastName(), "surname1");
    }

    @Test
    public void shouldFindUserByFirstNameWithOrderingByLastNameASC() {
        Ordering ordering = new Ordering("last name", "ASCENDING");
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name2", "surname2"));
        users.add(new User("name1", "surname1"));
        Mockito.when(jpaUserRepository.findByFirstName("name")).thenReturn(users);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 2);
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name1");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname1");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getFirstName(), "name2");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getLastName(), "surname2");
    }

    @Test
    public void shouldFindUserByFirstNameWithPaging() {
        Paging paging = new Paging(1,1);
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", paging);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name1", "surname1"));
        users.add(new User("name2", "surname2"));
        Mockito.when(jpaUserRepository.findByFirstName("name")).thenReturn(users);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name1");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname1");
    }

    @Test
    public void shouldFindUserByFirstNameWithPagingAndOrderingByFirstNameASC() {
        Paging paging = new Paging(1,1);
        Ordering ordering = new Ordering("first name", "ASCENDING");
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", paging, ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name2", "surname2"));
        users.add(new User("name1", "surname1"));
        Mockito.when(jpaUserRepository.findByFirstName("name")).thenReturn(users);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name1");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname1");
    }

    @Test
    public void shouldFindUserByFirstNameWithPagingAndOrderingByFirstNameDSC() {
        Paging paging = new Paging(1,1);
        Ordering ordering = new Ordering("first name", "DESCENDING");
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", paging, ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name1", "surname1"));
        users.add(new User("name2", "surname2"));
        Mockito.when(jpaUserRepository.findByFirstName("name")).thenReturn(users);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name2");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname2");
    }

    @Test
    public void shouldFindUserByFirstNameWithPagingAndOrderingByLastNameDSC() {
        Paging paging = new Paging(1,1);
        Ordering ordering = new Ordering("last name", "DESCENDING");
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", paging, ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name1", "surname1"));
        users.add(new User("name2", "surname2"));
        Mockito.when(jpaUserRepository.findByFirstName("name")).thenReturn(users);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name2");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname2");
    }

    @Test
    public void shouldFindUserByFirstNameWithPagingAndOrderingByLastNameASC() {
        Paging paging = new Paging(1,1);
        Ordering ordering = new Ordering("last name", "ASCENDING");
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", paging, ordering);
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(new ArrayList<>());
        users.add(new User("name2", "surname2"));
        users.add(new User("name1", "surname1"));
        Mockito.when(jpaUserRepository.findByFirstName("name")).thenReturn(users);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name1");
        Assertions.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname1");
    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("");
        errors.add(new CoreError("User first name", "must not be empty!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(errors);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertTrue(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getField(), "User first name");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest(null);
        errors.add(new CoreError("User first name", "must not be empty!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(errors);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertTrue(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getField(), "User first name");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", ordering);
        errors.add(new CoreError("Order by", "must not be empty"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(errors);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertTrue(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getField(), "Order by");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void shouldReturnResponseWithErrors_v4() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", ordering);
        errors.add(new CoreError("Order by", "must contain FIRST NAME or LAST NAME only!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(errors);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertTrue(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getField(), "Order by");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getMessage(), "must contain FIRST NAME or LAST NAME only!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v5() {
        Ordering ordering = new Ordering("name", "");
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", ordering);
        errors.add(new CoreError("Order direction", "must not be empty"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(errors);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertTrue(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getField(), "Order direction");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void shouldReturnResponseWithErrors_v6() {
        Ordering ordering = new Ordering("name", "asc");
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", ordering);
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(errors);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertTrue(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getField(), "Order direction");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getMessage(), "must contain ASCENDING or DESCENDING only!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v7() {
        Ordering ordering = new Ordering("", "asc");
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", ordering);
        errors.add(new CoreError("User first name", "must not be empty!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(errors);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertTrue(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().size(), 2);
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getField(), "User first name");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(1).getField(), "Order direction");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(1).getMessage(), "must contain ASCENDING or DESCENDING only!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v8() {
        Paging paging = new Paging(-1,1);
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", paging);
        errors.add(new CoreError("Page number", "must be greater then 0!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(errors);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertTrue(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getField(), "Page number");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getMessage(), "must be greater then 0!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v9() {
        Paging paging = new Paging(null,1);
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", paging);
        errors.add(new CoreError("Page number", "must not be empty"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(errors);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertTrue(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getField(), "Page number");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void shouldReturnResponseWithErrors_v10() {
        Paging paging = new Paging(1,-1);
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", paging);
        errors.add(new CoreError("Page size", "must be greater then 0!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(errors);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertTrue(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getField(), "Page size");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getMessage(), "must be greater then 0!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v11() {
        Paging paging = new Paging(1,null);
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", paging);
        errors.add(new CoreError("Page size", "must not be empty"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(errors);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertTrue(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().size(), 1);
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getField(), "Page size");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void shouldReturnResponseWithErrors_v12() {
        Paging paging = new Paging(-1,null);
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name", paging);
        errors.add(new CoreError("Page number", "must be greater then 0!"));
        errors.add(new CoreError("Page size", "must not be empty"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(errors);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertTrue(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().size(), 2);
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getField(), "Page number");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getMessage(), "must be greater then 0!");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(1).getField(), "Page size");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(1).getMessage(), "must not be empty");
    }

    @Test
    public void shouldReturnResponseWithErrors_v13() {
        Paging paging = new Paging(-1,null);
        Ordering ordering = new Ordering("name", "dsc");
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest(null, paging, ordering);
        errors.add(new CoreError("User first name", "must not be empty!"));
        errors.add(new CoreError("Page number", "must be greater then 0!"));
        errors.add(new CoreError("Page size", "must not be empty"));
        errors.add(new CoreError("Order by", "must not be empty"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        Mockito.when(searchUserByLastNameValidator.validate(searchUsersByFirstNameRequest)).thenReturn(errors);
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assertions.assertTrue(searchUserByFirstNameResponse.hasErrors());
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().size(), 5);
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getField(), "User first name");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(3).getField(), "Order by");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(3).getMessage(), "must not be empty");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(4).getField(), "Order direction");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(4).getMessage(), "must contain ASCENDING or DESCENDING only!");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(1).getField(), "Page number");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(1).getMessage(), "must be greater then 0!");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(2).getField(), "Page size");
        Assertions.assertEquals(searchUserByFirstNameResponse.getErrorList().get(2).getMessage(), "must not be empty");
    }
}