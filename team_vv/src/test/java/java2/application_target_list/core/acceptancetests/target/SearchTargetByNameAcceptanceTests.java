package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.target.SearchTargetByNameRequest;
import java2.application_target_list.core.services.target.SearchTargetByNameService;
import java2.application_target_list.core.responses.target.SearchTargetByNameResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.Optional;

@SpringBootTest
public class SearchTargetByNameAcceptanceTests {

    @Autowired
    private SearchTargetByNameService searchTargetByNameService;
    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private DatabaseCleaner databaseCleaner;


    @BeforeEach
    public void setup(){
        databaseCleaner.clean();
        addTargetsToDB();

        ReflectionTestUtils.setField(searchTargetByNameService, "orderingEnabled", true);
        ReflectionTestUtils.setField(searchTargetByNameService, "pagingEnabled", true);
    }

    @Test
    public void shouldReturnList() {
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name");
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1L));
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name3");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(6L));
    }

    @Test
    public void shouldReturnListWithOrderingByNameAscending() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1L));
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name3");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(6L));
    }

    @Test
    public void shouldReturnListWithOrderingByNameDescending() {
        Ordering ordering = new Ordering("name", "DESCENDING");
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6L));
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name1");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionDescending() {
        Ordering ordering = new Ordering("description", "DESCENDING");
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6L));
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name1");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionAscending() {
        Ordering ordering = new Ordering("description", "ASCENDING");
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1L));
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name3");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(6L));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineAscending() {
        Ordering ordering = new Ordering("deadline", "ASCENDING");
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1L));
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name3");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(6L));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineDescending() {
        Ordering ordering = new Ordering("deadline", "DESCENDING");
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6L));
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name1");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldReturnListWithOrderingByNamePaging() {
        Paging paging = new Paging(1,1);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", paging);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldReturnListWithOrderingByNameAscendingAndPaging() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldReturnListWithOrderingByNameDescendingAndPaging() {
        Ordering ordering = new Ordering("name", "DESCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6L));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionDescendingWithPaging() {
        Ordering ordering = new Ordering("description", "DESCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6L));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionAscendingWithPaging() {
        Ordering ordering = new Ordering("description", "ASCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineAscendingWithPaging() {
        Ordering ordering = new Ordering("deadline", "ASCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineDescendingWithPaging() {
        Ordering ordering = new Ordering("deadline", "DESCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering,paging);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6L));
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
