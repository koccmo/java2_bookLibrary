package application_target_list.core.services;

import application_target_list.core.database.Database;
import application_target_list.core.database.Target;
import application_target_list.core.requests.Ordering;
import application_target_list.core.requests.Paging;
import application_target_list.core.requests.SearchTargetByNameRequest;
import application_target_list.core.responses.CoreError;
import application_target_list.core.responses.SearchTargetByNameResponse;
import application_target_list.core.validators.SearchTargetByNameValidator;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class SearchTargetByNameServiceTest extends TestCase {

    @Mock private Database database;
    @Mock private SearchTargetByNameValidator validator;
    @InjectMocks SearchTargetByNameService service;

    @Test
    public void shouldSearchTargetsByName() {
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name", "description", 1));
        Mockito.when(database.findByTargetName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetsList().size(), 1);
        assertEquals(response.getTargetsList().get(0).getName(), "name");
        assertEquals(response.getTargetsList().get(0).getDescription(), "description");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByNameWithOrderingByNameAscending() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name2", "description2", 1));
        targets.add(new Target("name1", "description1", 10));
        Mockito.when(database.findByTargetName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetsList().size(), 2);
        assertEquals(response.getTargetsList().get(0).getName(), "name1");
        assertEquals(response.getTargetsList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(10));
        assertEquals(response.getTargetsList().get(1).getName(), "name2");
        assertEquals(response.getTargetsList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(1).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByNameWithOrderingByNameDescending() {
        Ordering ordering = new Ordering("name", "DESCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name1", "description1", 10));
        targets.add(new Target("name2", "description2", 1));
        Mockito.when(database.findByTargetName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetsList().size(), 2);
        assertEquals(response.getTargetsList().get(0).getName(), "name2");
        assertEquals(response.getTargetsList().get(0).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(1));
        assertEquals(response.getTargetsList().get(1).getName(), "name1");
        assertEquals(response.getTargetsList().get(1).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(1).getDeadline()), Optional.of(10));
    }

    @Test
    public void shouldSearchTargetsByNameWithOrderingByDescriptionDescending() {
        Ordering ordering = new Ordering("description", "DESCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name1", "description1", 10));
        targets.add(new Target("name2", "description2", 1));
        Mockito.when(database.findByTargetName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetsList().size(), 2);
        assertEquals(response.getTargetsList().get(0).getName(), "name2");
        assertEquals(response.getTargetsList().get(0).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(1));
        assertEquals(response.getTargetsList().get(1).getName(), "name1");
        assertEquals(response.getTargetsList().get(1).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(1).getDeadline()), Optional.of(10));
    }

    @Test
    public void shouldSearchTargetsByNameWithOrderingByDescriptionAscending() {
        Ordering ordering = new Ordering("description", "ASCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name2", "description2", 1));
        targets.add(new Target("name1", "description1", 10));
        Mockito.when(database.findByTargetName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetsList().size(), 2);
        assertEquals(response.getTargetsList().get(0).getName(), "name1");
        assertEquals(response.getTargetsList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(10));
        assertEquals(response.getTargetsList().get(1).getName(), "name2");
        assertEquals(response.getTargetsList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(1).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByNameWithOrderingByDeadlineAscending() {
        Ordering ordering = new Ordering("deadline", "ASCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name2", "description2", 10));
        targets.add(new Target("name1", "description1", 1));
        Mockito.when(database.findByTargetName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetsList().size(), 2);
        assertEquals(response.getTargetsList().get(0).getName(), "name1");
        assertEquals(response.getTargetsList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(1));
        assertEquals(response.getTargetsList().get(1).getName(), "name2");
        assertEquals(response.getTargetsList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(1).getDeadline()), Optional.of(10));
    }

    @Test
    public void shouldSearchTargetsByNameWithOrderingByDeadlineDescending() {
        Ordering ordering = new Ordering("deadline", "DESCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name1", "description1", 1));
        targets.add(new Target("name2", "description2", 10));
        Mockito.when(database.findByTargetName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetsList().size(), 2);
        assertEquals(response.getTargetsList().get(0).getName(), "name2");
        assertEquals(response.getTargetsList().get(0).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(10));
        assertEquals(response.getTargetsList().get(1).getName(), "name1");
        assertEquals(response.getTargetsList().get(1).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(1).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByNameWithPaging() {
        Paging paging = new Paging(1, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name2", "description2", 10));
        targets.add(new Target("name1", "description1", 1));
        Mockito.when(database.findByTargetName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetsList().size(), 1);
        assertEquals(response.getTargetsList().get(0).getName(), "name2");
        assertEquals(response.getTargetsList().get(0).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(10));
    }

    @Test
    public void shouldSearchTargetsByNameWithPagingAndOrderingByNameAscending() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name2", "description2", 1));
        targets.add(new Target("name1", "description1", 10));
        Mockito.when(database.findByTargetName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetsList().size(), 1);
        assertEquals(response.getTargetsList().get(0).getName(), "name1");
        assertEquals(response.getTargetsList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(10));
    }

    @Test
    public void shouldSearchTargetsByNameWithPagingAndOrderingByNameDescending() {
        Ordering ordering = new Ordering("name", "DESCENDING");
        Paging paging = new Paging(1, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name1", "description1", 10));
        targets.add(new Target("name2", "description2", 1));
        Mockito.when(database.findByTargetName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetsList().size(), 1);
        assertEquals(response.getTargetsList().get(0).getName(), "name2");
        assertEquals(response.getTargetsList().get(0).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByNameWithPagingAndOrderingByDescriptionDescending() {
        Ordering ordering = new Ordering("description", "DESCENDING");
        Paging paging = new Paging(1, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name1", "description1", 10));
        targets.add(new Target("name2", "description2", 1));
        Mockito.when(database.findByTargetName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetsList().size(), 1);
        assertEquals(response.getTargetsList().get(0).getName(), "name2");
        assertEquals(response.getTargetsList().get(0).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByNameWithPagingAndOrderingByDescriptionAscending() {
        Ordering ordering = new Ordering("description", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name2", "description2", 1));
        targets.add(new Target("name1", "description1", 10));
        Mockito.when(database.findByTargetName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetsList().size(), 1);
        assertEquals(response.getTargetsList().get(0).getName(), "name1");
        assertEquals(response.getTargetsList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(10));
    }

    @Test
    public void shouldSearchTargetsByNameWithPagingAndOrderingByDeadlineAscending() {
        Ordering ordering = new Ordering("deadline", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name2", "description2", 10));
        targets.add(new Target("name1", "description1", 1));
        Mockito.when(database.findByTargetName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetsList().size(), 1);
        assertEquals(response.getTargetsList().get(0).getName(), "name1");
        assertEquals(response.getTargetsList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldSearchTargetsByNameWithPagingAndOrderingByDeadlineDescending() {
        Ordering ordering = new Ordering("deadline", "DESCENDING");
        Paging paging = new Paging(1, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name1", "description1", 1));
        targets.add(new Target("name2", "description2", 10));
        Mockito.when(database.findByTargetName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetsList().size(), 1);
        assertEquals(response.getTargetsList().get(0).getName(), "name2");
        assertEquals(response.getTargetsList().get(0).getDescription(), "description2");
        assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(10));
    }

    @Test
    public void invalidSearchTargetByNameRequest_v1() {
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target name", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target name");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v2() {
        SearchTargetByNameRequest request = new SearchTargetByNameRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target name", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target name");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v3() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Order by", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Order by");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v4() {
        Ordering ordering = new Ordering("", "ASCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Order by", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Order by");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v5() {
        Ordering ordering = new Ordering("sad", "ASCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Order by");
        assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v6() {
        Ordering ordering = new Ordering("sad", null);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Order by");
        assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        assertEquals(response.getErrorList().get(1).getField(), "Order direction");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v7() {
        Ordering ordering = new Ordering("sad", "");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Order by");
        assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        assertEquals(response.getErrorList().get(1).getField(), "Order direction");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v8() {
        Ordering ordering = new Ordering("sad", "dsv");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Order by");
        assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        assertEquals(response.getErrorList().get(1).getField(), "Order direction");
        assertEquals(response.getErrorList().get(1).getMessage(), "must contain ASCENDING or DESCENDING only!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v9() {
        Ordering ordering = new Ordering("sad", "dsv");
        Paging paging = new Paging(-2, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        errors.add(new CoreError("Page number", "must be greater then 0!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 3);
        assertEquals(response.getErrorList().get(0).getField(), "Order by");
        assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        assertEquals(response.getErrorList().get(1).getField(), "Order direction");
        assertEquals(response.getErrorList().get(1).getMessage(), "must contain ASCENDING or DESCENDING only!");
        assertEquals(response.getErrorList().get(2).getField(), "Page number");
        assertEquals(response.getErrorList().get(2).getMessage(), "must be greater then 0!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v10() {
        Ordering ordering = new Ordering("sad", "dsv");
        Paging paging = new Paging(null, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        errors.add(new CoreError("Page number", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 3);
        assertEquals(response.getErrorList().get(0).getField(), "Order by");
        assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        assertEquals(response.getErrorList().get(1).getField(), "Order direction");
        assertEquals(response.getErrorList().get(1).getMessage(), "must contain ASCENDING or DESCENDING only!");
        assertEquals(response.getErrorList().get(2).getField(), "Page number");
        assertEquals(response.getErrorList().get(2).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v11() {
        Ordering ordering = new Ordering("sad", "dsv");
        Paging paging = new Paging(null, -1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        errors.add(new CoreError("Page number", "must not be empty"));
        errors.add(new CoreError("Page size", "must be greater then 0!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 4);
        assertEquals(response.getErrorList().get(0).getField(), "Order by");
        assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        assertEquals(response.getErrorList().get(1).getField(), "Order direction");
        assertEquals(response.getErrorList().get(1).getMessage(), "must contain ASCENDING or DESCENDING only!");
        assertEquals(response.getErrorList().get(2).getField(), "Page number");
        assertEquals(response.getErrorList().get(2).getMessage(), "must not be empty");
        assertEquals(response.getErrorList().get(3).getField(), "Page size");
        assertEquals(response.getErrorList().get(3).getMessage(), "must be greater then 0!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v12() {
        Ordering ordering = new Ordering("sad", "dsv");
        Paging paging = new Paging(null, null);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        errors.add(new CoreError("Page number", "must not be empty"));
        errors.add(new CoreError("Page size", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 4);
        assertEquals(response.getErrorList().get(0).getField(), "Order by");
        assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        assertEquals(response.getErrorList().get(1).getField(), "Order direction");
        assertEquals(response.getErrorList().get(1).getMessage(), "must contain ASCENDING or DESCENDING only!");
        assertEquals(response.getErrorList().get(2).getField(), "Page number");
        assertEquals(response.getErrorList().get(2).getMessage(), "must not be empty");
        assertEquals(response.getErrorList().get(3).getField(), "Page size");
        assertEquals(response.getErrorList().get(3).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v13() {
        Paging paging = new Paging(1, null);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Page size", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Page size");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v14() {
        Paging paging = new Paging(1, -2);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Page size", "must be greater then 0!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Page size");
        assertEquals(response.getErrorList().get(0).getMessage(), "must be greater then 0!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v15() {
        Paging paging = new Paging(-6, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Page number", "must be greater then 0!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Page number");
        assertEquals(response.getErrorList().get(0).getMessage(), "must be greater then 0!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v16() {
        Paging paging = new Paging(null, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Page number", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Page number");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty");
    }
}