package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.target.SearchTargetByDescriptionRequest;
import java2.application_target_list.core.services.target.SearchTargetByDescriptionService;
import java2.application_target_list.core.responses.target.SearchTargetByDescriptionResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.config.TargetListConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SearchByDescriptionAcceptanceTests {

    private SearchTargetByDescriptionService searchTargetByDescriptionService;

    @Before
    public void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        searchTargetByDescriptionService = applicationContext.getBean(SearchTargetByDescriptionService.class);
        AddTargetService addTargetService = applicationContext.getBean(AddTargetService.class);

        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name1", "description1", 1);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4);
        AddTargetRequest addTargetRequest3 = new AddTargetRequest("name3", "description3", 6);
        AddTargetRequest addTargetRequest4 = new AddTargetRequest("wdc", "sda", 156);

        addTargetService.execute(addTargetRequest1);
        addTargetService.execute(addTargetRequest2);
        addTargetService.execute(addTargetRequest3);
        addTargetService.execute(addTargetRequest4);

        ReflectionTestUtils.setField(searchTargetByDescriptionService, "orderingEnabled", true);
        ReflectionTestUtils.setField(searchTargetByDescriptionService, "pagingEnabled", true);
    }

    @Test
    public void shouldReturnList() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description");
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertNull(searchTargetByDescriptionResponse.getErrorList());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name2");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(4));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "name3");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(6));
    }

    @Test
    public void shouldReturnListWithOrderingByNameAscending() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description", ordering);
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertNull(searchTargetByDescriptionResponse.getErrorList());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name2");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(4));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "name3");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(6));
    }

    @Test
    public void shouldReturnListWithOrderingByNameDescending() {
        Ordering ordering = new Ordering("name", "DESCENDING");
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description", ordering);
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertNull(searchTargetByDescriptionResponse.getErrorList());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name3");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(6));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name2");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(4));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "name1");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionDescending() {
        Ordering ordering = new Ordering("description", "DESCENDING");
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description", ordering);
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertNull(searchTargetByDescriptionResponse.getErrorList());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name3");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(6));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name2");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(4));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "name1");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineDescending() {
        Ordering ordering = new Ordering("deadline", "DESCENDING");
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description", ordering);
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertNull(searchTargetByDescriptionResponse.getErrorList());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name3");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(6));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name2");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(4));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "name1");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionAscending() {
        Ordering ordering = new Ordering("description", "ASCENDING");
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description", ordering);
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertNull(searchTargetByDescriptionResponse.getErrorList());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name2");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(4));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "name3");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(6));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineAscending() {
        Ordering ordering = new Ordering("deadline", "ASCENDING");
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description", ordering);
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertNull(searchTargetByDescriptionResponse.getErrorList());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name2");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(4));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "name3");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(6));
    }

    @Test
    public void shouldReturnListWithPaging() {
        Paging paging = new Paging(1,1);
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description", paging);
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertNull(searchTargetByDescriptionResponse.getErrorList());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 1);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldReturnListWithOrderingByNameAscendingWithPaging() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description", ordering, paging);
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertNull(searchTargetByDescriptionResponse.getErrorList());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 1);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldReturnListWithOrderingByNameDescendingWithPaging() {
        Ordering ordering = new Ordering("name", "DESCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description", ordering, paging);
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertNull(searchTargetByDescriptionResponse.getErrorList());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 1);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name3");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(6));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionDescendingWithPaging() {
        Ordering ordering = new Ordering("description", "DESCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description", ordering, paging);
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertNull(searchTargetByDescriptionResponse.getErrorList());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 1);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name3");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(6));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineDescendingWithPaging() {
        Ordering ordering = new Ordering("deadline", "DESCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description", ordering, paging);
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertNull(searchTargetByDescriptionResponse.getErrorList());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 1);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name3");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(6));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionAscendingWithPaging() {
        Ordering ordering = new Ordering("description", "ASCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description", ordering, paging);
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertNull(searchTargetByDescriptionResponse.getErrorList());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 1);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineAscendingWithPaging() {
        Ordering ordering = new Ordering("deadline", "ASCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description", ordering, paging);
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertNull(searchTargetByDescriptionResponse.getErrorList());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 1);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name1");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(1));
    }

}
