package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.TargetDatabase;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.target.SearchTargetByDescriptionRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.SearchTargetByDescriptionResponse;
import java2.application_target_list.core.services.target.SearchTargetByDescriptionService;
import java2.application_target_list.core.validators.target.SearchTargetByDescriptionValidator;
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
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class SearchTargetByDescriptionServiceTest extends TestCase {

    private List<Target> targets;
    private List<CoreError> errors;
    @Mock private TargetDatabase targetDatabase;
    @Mock private SearchTargetByDescriptionValidator validator;
    @InjectMocks
    SearchTargetByDescriptionService service;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
        targets = new ArrayList<>();
        errors = new ArrayList<>();
    }

    @Test
    public void shouldSearchTargetsByDescription() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name", "description", 1));
        Mockito.when(targetDatabase.findByTargetDescription("description")).thenReturn(targets);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 1);
        assertEquals(response.getTargetList().get(0).getName(), "name");
        assertEquals(response.getTargetList().get(0).getDescription(), "description");
        assertEquals(Optional.ofNullable(response.getTargetList().get(0).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByDescriptionWithOrderingByNameAscending() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name2", "description2", 1));
        targets.add(new Target("name1", "description1", 10));
        Mockito.when(targetDatabase.findByTargetDescription("description")).thenReturn(targets);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 2);
        assertEquals(response.getTargetList().get(0).getName(), "name1");
        assertEquals(response.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetList().get(0).getDeadline()), Optional.of(10));
        assertEquals(response.getTargetList().get(1).getName(), "name2");
        assertEquals(response.getTargetList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetList().get(1).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByDescriptionWithOrderingByNameDescending() {
        Ordering ordering = new Ordering("name", "DESCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name1", "description1", 1));
        targets.add(new Target("name2", "description2", 10));
        Mockito.when(targetDatabase.findByTargetDescription("description")).thenReturn(targets);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 2);
        assertEquals(response.getTargetList().get(0).getName(), "name2");
        assertEquals(response.getTargetList().get(0).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetList().get(0).getDeadline()), Optional.of(10));
        assertEquals(response.getTargetList().get(1).getName(), "name1");
        assertEquals(response.getTargetList().get(1).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetList().get(1).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByDescriptionWithOrderingByDescriptionDescending() {
        Ordering ordering = new Ordering("description", "DESCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name1", "description1", 1));
        targets.add(new Target("name2", "description2", 10));
        Mockito.when(targetDatabase.findByTargetDescription("description")).thenReturn(targets);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 2);
        assertEquals(response.getTargetList().get(0).getName(), "name2");
        assertEquals(response.getTargetList().get(0).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetList().get(0).getDeadline()), Optional.of(10));
        assertEquals(response.getTargetList().get(1).getName(), "name1");
        assertEquals(response.getTargetList().get(1).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetList().get(1).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByDescriptionWithOrderingByDescriptionAscending() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name2", "description2", 1));
        targets.add(new Target("name1", "description1", 10));
        Mockito.when(targetDatabase.findByTargetDescription("description")).thenReturn(targets);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 2);
        assertEquals(response.getTargetList().get(0).getName(), "name1");
        assertEquals(response.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetList().get(0).getDeadline()), Optional.of(10));
        assertEquals(response.getTargetList().get(1).getName(), "name2");
        assertEquals(response.getTargetList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetList().get(1).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByDescriptionWithOrderingByDeadlineAscending() {
        Ordering ordering = new Ordering("deadline", "ASCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name2", "description2", 10));
        targets.add(new Target("name1", "description1", 1));
        Mockito.when(targetDatabase.findByTargetDescription("description")).thenReturn(targets);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 2);
        assertEquals(response.getTargetList().get(0).getName(), "name1");
        assertEquals(response.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetList().get(0).getDeadline()), Optional.of(1));
        assertEquals(response.getTargetList().get(1).getName(), "name2");
        assertEquals(response.getTargetList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetList().get(1).getDeadline()), Optional.of(10));
    }

    @Test
    public void shouldSearchTargetsByDescriptionWithOrderingByDeadlineDescending() {
        Ordering ordering = new Ordering("deadline", "DESCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name1", "description1", 1));
        targets.add(new Target("name2", "description2", 10));
        Mockito.when(targetDatabase.findByTargetDescription("description")).thenReturn(targets);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 2);
        assertEquals(response.getTargetList().get(0).getName(), "name2");
        assertEquals(response.getTargetList().get(0).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetList().get(0).getDeadline()), Optional.of(10));
        assertEquals(response.getTargetList().get(1).getName(), "name1");
        assertEquals(response.getTargetList().get(1).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetList().get(1).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByDescriptionWithPaging() {
        Paging paging = new Paging(1,1);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name1", "description1", 1));
        targets.add(new Target("name2", "description2", 10));
        Mockito.when(targetDatabase.findByTargetDescription("description")).thenReturn(targets);

        SearchTargetByDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 1);
        assertEquals(response.getTargetList().get(0).getName(), "name1");
        assertEquals(response.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetList().get(0).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByDescriptionWithPagingAndOrderingByNameAscending() {
        Paging paging = new Paging(1,1);
        Ordering ordering = new Ordering("name", "ASCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name2", "description2", 1));
        targets.add(new Target("name1", "description1", 10));
        Mockito.when(targetDatabase.findByTargetDescription("description")).thenReturn(targets);

        SearchTargetByDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 1);
        assertEquals(response.getTargetList().get(0).getName(), "name1");
        assertEquals(response.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetList().get(0).getDeadline()), Optional.of(10));
    }

    @Test
    public void shouldSearchTargetsByDescriptionWithPagingAndOrderingByNameDescending() {
        Paging paging = new Paging(1,1);
        Ordering ordering = new Ordering("name", "DESCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name1", "description1", 1));
        targets.add(new Target("name2", "description2", 10));
        Mockito.when(targetDatabase.findByTargetDescription("description")).thenReturn(targets);

        SearchTargetByDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 1);
        assertEquals(response.getTargetList().get(0).getName(), "name2");
        assertEquals(response.getTargetList().get(0).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetList().get(0).getDeadline()), Optional.of(10));
    }

    @Test
    public void shouldSearchTargetsByDescriptionWithPagingAndOrderingByDescriptionDescending() {
        Paging paging = new Paging(1,1);
        Ordering ordering = new Ordering("description", "DESCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name1", "description1", 1));
        targets.add(new Target("name2", "description2", 10));
        Mockito.when(targetDatabase.findByTargetDescription("description")).thenReturn(targets);

        SearchTargetByDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 1);
        assertEquals(response.getTargetList().get(0).getName(), "name2");
        assertEquals(response.getTargetList().get(0).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetList().get(0).getDeadline()), Optional.of(10));
    }

    @Test
    public void shouldSearchTargetsByDescriptionWithPagingAndOrderingByDescriptionAscending() {
        Paging paging = new Paging(1,1);
        Ordering ordering = new Ordering("description", "ASCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name2", "description2", 1));
        targets.add(new Target("name1", "description1", 10));
        Mockito.when(targetDatabase.findByTargetDescription("description")).thenReturn(targets);

        SearchTargetByDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 1);
        assertEquals(response.getTargetList().get(0).getName(), "name1");
        assertEquals(response.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetList().get(0).getDeadline()), Optional.of(10));
    }

    @Test
    public void shouldSearchTargetsByDescriptionWithPagingAndOrderingByDeadlineAscending() {
        Paging paging = new Paging(1,1);
        Ordering ordering = new Ordering("deadline", "ASCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name2", "description2", 10));
        targets.add(new Target("name1", "description1", 1));
        Mockito.when(targetDatabase.findByTargetDescription("description")).thenReturn(targets);

        SearchTargetByDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 1);
        assertEquals(response.getTargetList().get(0).getName(), "name1");
        assertEquals(response.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetList().get(0).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByDescriptionWithPagingAndOrderingByDeadlineDescending() {
        Paging paging = new Paging(1,1);
        Ordering ordering = new Ordering("deadline", "DESCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name2", "description2", 1));
        targets.add(new Target("name1", "description1", 10));
        Mockito.when(targetDatabase.findByTargetDescription("description")).thenReturn(targets);

        SearchTargetByDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 1);
        assertEquals(response.getTargetList().get(0).getName(), "name1");
        assertEquals(response.getTargetList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetList().get(0).getDeadline()), Optional.of(10));
    }

    @Test
    public void invalidSearchTargetsByDescriptionRequest_v1() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("");
        errors.add(new CoreError("Target description", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target description");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidSearchTargetsByDescriptionRequest_v2() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest(null);
        errors.add(new CoreError("Target description", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target description");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidSearchTargetsByDescriptionRequest_v3() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest(null, ordering);
        errors.add(new CoreError("Target description", "must not be empty!"));
        errors.add(new CoreError("Order by", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target description");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(response.getErrorList().get(1).getField(), "Order by");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetsByDescriptionRequest_v4() {
        Ordering ordering = new Ordering(null, "");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest(null, ordering);
        errors.add(new CoreError("Target description", "must not be empty!"));
        errors.add(new CoreError("Order by", "must not be empty"));
        errors.add(new CoreError("Order direction", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 3);
        assertEquals(response.getErrorList().get(0).getField(), "Target description");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(response.getErrorList().get(1).getField(), "Order by");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty");
        assertEquals(response.getErrorList().get(2).getField(), "Order direction");
        assertEquals(response.getErrorList().get(2).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetsByDescriptionRequest_v5() {
        Ordering ordering = new Ordering("na", "");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest(null, ordering);
        errors.add(new CoreError("Target description", "must not be empty!"));
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 3);
        assertEquals(response.getErrorList().get(0).getField(), "Target description");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(response.getErrorList().get(1).getField(), "Order by");
        assertEquals(response.getErrorList().get(1).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        assertEquals(response.getErrorList().get(2).getField(), "Order direction");
        assertEquals(response.getErrorList().get(2).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetsByDescriptionRequest_v6() {
        Ordering ordering = new Ordering("na", "xf");
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest(null, ordering);
        errors.add(new CoreError("Target description", "must not be empty!"));
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 3);
        assertEquals(response.getErrorList().get(0).getField(), "Target description");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(response.getErrorList().get(1).getField(), "Order by");
        assertEquals(response.getErrorList().get(1).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        assertEquals(response.getErrorList().get(2).getField(), "Order direction");
        assertEquals(response.getErrorList().get(2).getMessage(), "must contain ASCENDING or DESCENDING only!");
    }

    @Test
    public void invalidSearchTargetsByDescriptionRequest_v7() {
        Ordering ordering = new Ordering("na", "xf");
        Paging paging = new Paging(-1, 1);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest(null, ordering, paging);
        errors.add(new CoreError("Target description", "must not be empty!"));
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        errors.add(new CoreError("Page number", "must be greater then 0!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 4);
        assertEquals(response.getErrorList().get(0).getField(), "Target description");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(response.getErrorList().get(1).getField(), "Order by");
        assertEquals(response.getErrorList().get(1).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        assertEquals(response.getErrorList().get(2).getField(), "Order direction");
        assertEquals(response.getErrorList().get(2).getMessage(), "must contain ASCENDING or DESCENDING only!");
        assertEquals(response.getErrorList().get(3).getField(), "Page number");
        assertEquals(response.getErrorList().get(3).getMessage(), "must be greater then 0!");
    }

    @Test
    public void invalidSearchTargetsByDescriptionRequest_v8() {
        Ordering ordering = new Ordering("na", "xf");
        Paging paging = new Paging(null, 1);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest(null, ordering, paging);
        errors.add(new CoreError("Target description", "must not be empty!"));
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        errors.add(new CoreError("Page number", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 4);
        assertEquals(response.getErrorList().get(0).getField(), "Target description");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(response.getErrorList().get(1).getField(), "Order by");
        assertEquals(response.getErrorList().get(1).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        assertEquals(response.getErrorList().get(2).getField(), "Order direction");
        assertEquals(response.getErrorList().get(2).getMessage(), "must contain ASCENDING or DESCENDING only!");
        assertEquals(response.getErrorList().get(3).getField(), "Page number");
        assertEquals(response.getErrorList().get(3).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetsByDescriptionRequest_v9() {
        Ordering ordering = new Ordering("na", "xf");
        Paging paging = new Paging(null, -1);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest(null, ordering, paging);
        errors.add(new CoreError("Target description", "must not be empty!"));
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        errors.add(new CoreError("Page number", "must not be empty"));
        errors.add(new CoreError("Page size", "must be greater then 0!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 5);
        assertEquals(response.getErrorList().get(0).getField(), "Target description");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(response.getErrorList().get(1).getField(), "Order by");
        assertEquals(response.getErrorList().get(1).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        assertEquals(response.getErrorList().get(2).getField(), "Order direction");
        assertEquals(response.getErrorList().get(2).getMessage(), "must contain ASCENDING or DESCENDING only!");
        assertEquals(response.getErrorList().get(3).getField(), "Page number");
        assertEquals(response.getErrorList().get(3).getMessage(), "must not be empty");
        assertEquals(response.getErrorList().get(4).getField(), "Page size");
        assertEquals(response.getErrorList().get(4).getMessage(), "must be greater then 0!");
    }

    @Test
    public void invalidSearchTargetsByDescriptionRequest_v10() {
        Ordering ordering = new Ordering("na", "xf");
        Paging paging = new Paging(null, null);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest(null, ordering, paging);
        errors.add(new CoreError("Target description", "must not be empty!"));
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        errors.add(new CoreError("Page number", "must not be empty"));
        errors.add(new CoreError("Page size", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 5);
        assertEquals(response.getErrorList().get(0).getField(), "Target description");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(response.getErrorList().get(1).getField(), "Order by");
        assertEquals(response.getErrorList().get(1).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        assertEquals(response.getErrorList().get(2).getField(), "Order direction");
        assertEquals(response.getErrorList().get(2).getMessage(), "must contain ASCENDING or DESCENDING only!");
        assertEquals(response.getErrorList().get(3).getField(), "Page number");
        assertEquals(response.getErrorList().get(3).getMessage(), "must not be empty");
        assertEquals(response.getErrorList().get(4).getField(), "Page size");
        assertEquals(response.getErrorList().get(4).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetsByDescriptionRequest_v11() {
        Paging paging = new Paging(null, 1);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", paging);
        errors.add(new CoreError("Page number", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Page number");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetsByDescriptionRequest_v12() {
        Paging paging = new Paging(-2, 1);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", paging);
        errors.add(new CoreError("Page number", "must be greater then 0!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Page number");
        assertEquals(response.getErrorList().get(0).getMessage(), "must be greater then 0!");
    }

    @Test
    public void invalidSearchTargetsByDescriptionRequest_v13() {
        Paging paging = new Paging(-2, null);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", paging);
        errors.add(new CoreError("Page number", "must be greater then 0!"));
        errors.add(new CoreError("Page size", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Page number");
        assertEquals(response.getErrorList().get(0).getMessage(), "must be greater then 0!");
        assertEquals(response.getErrorList().get(1).getField(), "Page size");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetsByDescriptionRequest_v14() {
        Paging paging = new Paging(-2, -2);
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description", paging);
        errors.add(new CoreError("Page number", "must be greater then 0!"));
        errors.add(new CoreError("Page size", "must be greater then 0!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Page number");
        assertEquals(response.getErrorList().get(0).getMessage(), "must be greater then 0!");
        assertEquals(response.getErrorList().get(1).getField(), "Page size");
        assertEquals(response.getErrorList().get(1).getMessage(), "must be greater then 0!");
    }
}