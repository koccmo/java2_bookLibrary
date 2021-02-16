package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.config.SpringCoreConfiguration;
import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.SearchUsersByLastNameRequest;
import java2.application_target_list.core.responses.user.SearchUserByLastNameResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.SearchUserByLastNameService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SearchUserByLastNameAcceptanceTest {

    private SearchUserByLastNameService searchUserByLastNameService;
    private ApplicationContext applicationContext;
    private AddUserService addUserService;
    private DatabaseCleaner databaseCleaner;

    @Before
    public void setup(){
        createServices();
        databaseCleaner.clean();
        addUsersToDatabase();

        ReflectionTestUtils.setField(searchUserByLastNameService, "orderingEnabled", true);
        ReflectionTestUtils.setField(searchUserByLastNameService, "pagingEnabled", true);
    }

    @Test
    public void shouldReturnList(){
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname");
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);

        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 3);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getFirstName(), "name2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getLastName(), "surname2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(2).getFirstName(), "name3");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(2).getLastName(), "surname3");
    }

    @Test
    public void shouldReturnListWithPaging(){
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname",
                new Paging(1,1));
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);

        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname1");
    }

    @Test
    public void shouldReturnListWithOrderingByFirstNameASC(){
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname",
                new Ordering("first name", "ASCENDING"));
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);

        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 3);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getFirstName(), "name2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getLastName(), "surname2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(2).getFirstName(), "name3");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(2).getLastName(), "surname3");
    }

    @Test
    public void shouldReturnListWithOrderingByLastNameASC(){
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname",
                new Ordering("last name", "ASCENDING"));
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);

        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 3);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getFirstName(), "name2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getLastName(), "surname2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(2).getFirstName(), "name3");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(2).getLastName(), "surname3");
    }

    @Test
    public void shouldReturnListWithOrderingByLastNameDSC(){
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname",
                new Ordering("last name", "DESCENDING"));
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);

        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 3);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(2).getFirstName(), "name1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(2).getLastName(), "surname1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getFirstName(), "name2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getLastName(), "surname2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name3");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname3");
    }

    @Test
    public void shouldReturnListWithOrderingByFirstNameDSC(){
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname",
                new Ordering("first name", "DESCENDING"));
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);

        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 3);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(2).getFirstName(), "name1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(2).getLastName(), "surname1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getFirstName(), "name2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getLastName(), "surname2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name3");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname3");
    }

    @Test
    public void shouldReturnListWithPagingAndWithOrderingByFirstNameASC(){
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname",
                new Paging(1,1),
                new Ordering("first name", "ASCENDING"));
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);

        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname1");
    }

    @Test
    public void shouldReturnListWithPagingAndOrderingByLastNameASC(){
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname",
                new Paging(1, 2),
                new Ordering("last name", "ASCENDING"));
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);

        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 2);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname1");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getFirstName(), "name2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getLastName(), "surname2");
    }

    @Test
    public void shouldReturnListWithPagingAndOrderingByLastNameDSC(){
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname",
                new Paging(1,1),
                new Ordering("last name", "DESCENDING"));
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);

        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 1);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name3");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname3");
    }

    @Test
    public void shouldReturnListWithPagingAndOrderingByFirstNameDSC(){
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("surname",
                new Paging(1,2),
                new Ordering("first name", "DESCENDING"));
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);

        assertFalse(searchUserByLastNameResponse.hasErrors());
        assertEquals(searchUserByLastNameResponse.getUsersList().size(), 2);
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getFirstName(), "name2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(1).getLastName(), "surname2");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name3");
        assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "surname3");
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


    private void addUsersToDatabase() {
        AddUserRequest addUserRequest1 = new AddUserRequest("name1", "surname1");
        AddUserRequest addUserRequest2 = new AddUserRequest("name2", "surname2");
        AddUserRequest addUserRequest3 = new AddUserRequest("name3", "surname3");
        AddUserRequest addUserRequest4 = new AddUserRequest("asd", "qwe");

        addUserService.execute(addUserRequest1);
        addUserService.execute(addUserRequest2);
        addUserService.execute(addUserRequest3);
        addUserService.execute(addUserRequest4);
    }

    private void createServices() {
        applicationContext = createApplicationContext();
        searchUserByLastNameService = createSearchUserByLastNameService();
        addUserService = createAddUserService();
        databaseCleaner = createDatabaseCleaner();
    }
}
