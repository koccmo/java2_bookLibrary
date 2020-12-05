//package application_target_list.core.acceptancetests;
//
//import application_target_list.dependency_injection.ApplicationContext;
//import application_target_list.core.requests.AddTargetRequest;
//import application_target_list.core.requests.Ordering;
//import application_target_list.core.requests.Paging;
//import application_target_list.core.requests.SearchTargetByNameRequest;
//import application_target_list.core.responses.SearchTargetByNameResponse;
//import application_target_list.core.services.AddTargetService;
//import application_target_list.core.services.SearchTargetByNameService;
//import application_target_list.dependency_injection.DIApplicationContextBuilder;
//import org.junit.Before;
//import org.junit.Test;
//
//
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//
//public class SearchTargetByNameAcceptanceTests {
//
//    private static final ApplicationContext applicationContext =
//            new DIApplicationContextBuilder().build("application_target_list");
//
//    @Before
//            public void addTargets(){
//        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name1", "description1", 1);
//        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4);
//        AddTargetRequest addTargetRequest3 = new AddTargetRequest("name3", "description3", 6);
//        AddTargetRequest addTargetRequest4 = new AddTargetRequest("wdc", "sda", 156);
//        AddTargetService addTargetService = applicationContext.getBean(AddTargetService.class);
//        addTargetService.execute(addTargetRequest1);
//        addTargetService.execute(addTargetRequest2);
//        addTargetService.execute(addTargetRequest3);
//        addTargetService.execute(addTargetRequest4);
//    }
//
//
//    @Test
//    public void shouldReturnList() {
//        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
//        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name");
//        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
//
//        assertNull(searchTargetByNameResponse.getErrorList());
//        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4));
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name3");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description3");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(6));
//    }
//
//    @Test
//    public void shouldReturnListWithOrderingByNameAscending() {
//        Ordering ordering = new Ordering("name", "ASCENDING");
//        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
//        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
//        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
//
//        assertNull(searchTargetByNameResponse.getErrorList());
//        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4));
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name3");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description3");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(6));
//    }
//
//    @Test
//    public void shouldReturnListWithOrderingByNameDescending() {
//        Ordering ordering = new Ordering("name", "DESCENDING");
//        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
//        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
//        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
//
//        assertNull(searchTargetByNameResponse.getErrorList());
//        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6));
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4));
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name1");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description1");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(1));
//    }
//
//    @Test
//    public void shouldReturnListWithOrderingByDescriptionDescending() {
//        Ordering ordering = new Ordering("description", "DESCENDING");
//        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
//        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
//        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
//
//        assertNull(searchTargetByNameResponse.getErrorList());
//        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6));
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4));
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name1");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description1");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(1));
//    }
//
//    @Test
//    public void shouldReturnListWithOrderingByDescriptionAscending() {
//        Ordering ordering = new Ordering("description", "ASCENDING");
//        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
//        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
//        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
//
//        assertNull(searchTargetByNameResponse.getErrorList());
//        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4));
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name3");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description3");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(6));
//    }
//
//    @Test
//    public void shouldReturnListWithOrderingByDeadlineAscending() {
//        Ordering ordering = new Ordering("deadline", "ASCENDING");
//        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
//        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
//        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
//
//        assertNull(searchTargetByNameResponse.getErrorList());
//        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4));
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name3");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description3");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(6));
//    }
//
//    @Test
//    public void shouldReturnListWithOrderingByDeadlineDescending() {
//        Ordering ordering = new Ordering("deadline", "DESCENDING");
//        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
//        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
//        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
//
//        assertNull(searchTargetByNameResponse.getErrorList());
//        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6));
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4));
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name1");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description1");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(1));
//    }
//
//    @Test
//    public void shouldReturnListWithOrderingByNamePaging() {
//        Paging paging = new Paging(1,1);
//        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
//        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", paging);
//        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
//
//        assertNull(searchTargetByNameResponse.getErrorList());
//        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
//    }
//
//    @Test
//    public void shouldReturnListWithOrderingByNameAscendingAndPaging() {
//        Ordering ordering = new Ordering("name", "ASCENDING");
//        Paging paging = new Paging(1,1);
//        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
//        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
//        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
//
//        assertNull(searchTargetByNameResponse.getErrorList());
//        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
//    }
//
//    @Test
//    public void shouldReturnListWithOrderingByNameDescendingAndPaging() {
//        Ordering ordering = new Ordering("name", "DESCENDING");
//        Paging paging = new Paging(1,1);
//        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
//        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
//        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
//
//        assertNull(searchTargetByNameResponse.getErrorList());
//        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6));
//    }
//
//    @Test
//    public void shouldReturnListWithOrderingByDescriptionDescendingWithPaging() {
//        Ordering ordering = new Ordering("description", "DESCENDING");
//        Paging paging = new Paging(1,1);
//        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
//        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
//        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
//
//        assertNull(searchTargetByNameResponse.getErrorList());
//        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6));
//    }
//
//    @Test
//    public void shouldReturnListWithOrderingByDescriptionAscendingWithPaging() {
//        Ordering ordering = new Ordering("description", "ASCENDING");
//        Paging paging = new Paging(1,1);
//        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
//        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
//        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
//
//        assertNull(searchTargetByNameResponse.getErrorList());
//        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
//    }
//
//    @Test
//    public void shouldReturnListWithOrderingByDeadlineAscendingWithPaging() {
//        Ordering ordering = new Ordering("deadline", "ASCENDING");
//        Paging paging = new Paging(1,1);
//        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
//        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
//        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
//
//        assertNull(searchTargetByNameResponse.getErrorList());
//        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
//    }
//
//    @Test
//    public void shouldReturnListWithOrderingByDeadlineDescendingWithPaging() {
//        Ordering ordering = new Ordering("deadline", "DESCENDING");
//        Paging paging = new Paging(1,1);
//        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
//        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering,paging);
//        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
//
//        assertNull(searchTargetByNameResponse.getErrorList());
//        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
//        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
//        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6));
//    }
//
//
//
//
//}
