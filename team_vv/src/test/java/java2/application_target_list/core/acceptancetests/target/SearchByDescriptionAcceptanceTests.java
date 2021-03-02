package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.target.SearchTargetByDescriptionRequest;
import java2.application_target_list.core.services.target.SearchTargetByDescriptionService;
import java2.application_target_list.core.responses.target.SearchTargetByDescriptionResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.Optional;

@SpringBootTest
public class SearchByDescriptionAcceptanceTests {

    @Autowired
    private SearchTargetByDescriptionService searchTargetByDescriptionService;
    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    public void setup(){
        databaseCleaner.clean();
        addTargetsToDB();

        ReflectionTestUtils.setField(searchTargetByDescriptionService, "orderingEnabled", true);
        ReflectionTestUtils.setField(searchTargetByDescriptionService, "pagingEnabled", true);
    }

    @Test
    public void shouldReturnList() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description");
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertNull(searchTargetByDescriptionResponse.getErrorList());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name2");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "name3");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(6L));
    }

    @Test
    public void shouldReturnListWithOrderingByNameAscending() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description",
                new Ordering("name", "ASCENDING"));
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertNull(searchTargetByDescriptionResponse.getErrorList());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name2");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "name3");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(6L));
    }

    @Test
    public void shouldReturnListWithOrderingByNameDescending() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description",
                new Ordering("name", "DESCENDING"));
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertNull(searchTargetByDescriptionResponse.getErrorList());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name3");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(6L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name2");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "name1");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionDescending() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description",
                new Ordering("description", "DESCENDING"));
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertNull(searchTargetByDescriptionResponse.getErrorList());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name3");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(6L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name2");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "name1");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineDescending() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description",
                new Ordering("deadline", "DESCENDING"));
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertNull(searchTargetByDescriptionResponse.getErrorList());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name3");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(6L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name2");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "name1");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionAscending() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description",
                new Ordering("description", "ASCENDING"));
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertNull(searchTargetByDescriptionResponse.getErrorList());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name2");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "name3");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(6L));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineAscending() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description",
                new Ordering("deadline", "ASCENDING"));
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertNull(searchTargetByDescriptionResponse.getErrorList());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name2");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "name3");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(6L));
    }

    @Test
    public void shouldReturnListWithPaging() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description",
                new Paging(1,1));
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertNull(searchTargetByDescriptionResponse.getErrorList());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 1);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldReturnListWithOrderingByNameAscendingWithPaging() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description",
                new Ordering("name", "ASCENDING"),
                new Paging(1,1));
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertNull(searchTargetByDescriptionResponse.getErrorList());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 1);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldReturnListWithOrderingByNameDescendingWithPaging() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description",
                new Ordering("name", "DESCENDING"),
                new Paging(1,1));
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertNull(searchTargetByDescriptionResponse.getErrorList());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 1);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name3");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(6L));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionDescendingWithPaging() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description",
                new Ordering("description", "DESCENDING"),
                new Paging(1,1));
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertNull(searchTargetByDescriptionResponse.getErrorList());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 1);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name3");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(6L));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineDescendingWithPaging() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description",
                new Ordering("deadline", "DESCENDING"),
                new Paging(1,1));
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertNull(searchTargetByDescriptionResponse.getErrorList());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 1);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name3");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(6L));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionAscendingWithPaging() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description",
                new Ordering("description", "ASCENDING"),
                new Paging(1,1));
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertNull(searchTargetByDescriptionResponse.getErrorList());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 1);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineAscendingWithPaging() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description",
                new Ordering("deadline", "ASCENDING"),
                new Paging(1,1));
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertNull(searchTargetByDescriptionResponse.getErrorList());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 1);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1L));
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name1", "description1", 1L);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4L);
        AddTargetRequest addTargetRequest3 = new AddTargetRequest("name3", "description3", 6L);
        AddTargetRequest addTargetRequest4 = new AddTargetRequest("wdc", "sda", 156L);

        addTargetService.execute(addTargetRequest1);
        addTargetService.execute(addTargetRequest2);
        addTargetService.execute(addTargetRequest3);
        addTargetService.execute(addTargetRequest4);
    }
}
