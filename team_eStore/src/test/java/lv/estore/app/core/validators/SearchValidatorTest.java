package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.Ordering;
import lv.estore.app.core.request.Paging;
import lv.estore.app.core.request.SearchRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SearchValidatorTest {

    @Mock ValidationRules rules;

    @InjectMocks SearchValidator searchValidator;

    @Test
    public void testValidate_AllValid() {
        Mockito.when(rules.validateName("pen")).thenReturn(Optional.empty());
        Mockito.when(rules.validatePrice("1.10")).thenReturn(Optional.empty());
        Mockito.when(rules.validateOrderBy(any())).thenReturn(Optional.empty());
        Mockito.when(rules.validateDirection(any())).thenReturn(Optional.empty());
        Mockito.when(rules.validatePageSize(any())).thenReturn(Optional.empty());
        Mockito.when(rules.validatePageNumber(any())).thenReturn(Optional.empty());

        Ordering ordering = new Ordering("Name", "ASCENDING");
        Paging paging = new Paging(1,10);

        SearchRequest request = new SearchRequest("pen", "1.10", ordering, paging);
        List<CoreError> errors = searchValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testValidate_AllAbsent() {
        Ordering ordering = new Ordering(null, null);
        Paging paging = new Paging(null,null);

        SearchRequest request = new SearchRequest(null, null, ordering, paging);
        List<CoreError> errors = searchValidator.validate(request);
        assertEquals(2, errors.size());
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("Name") && s.getMessage().equals("Field should not be empty")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("Price") && s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidate_Invalid() {
        Mockito.when(rules.validatePrice("XYZ")).thenReturn(Optional.of(new CoreError("Price",  "Field should be valid")));
        Mockito.when(rules.validateOrderBy(any())).thenReturn(Optional.of(new CoreError("OrderBy",  "Field value should be valid 'name'/'price'")));
        Mockito.when(rules.validateDirection(any())).thenReturn(Optional.of(new CoreError("Direction",  "Value should be valid: 'ASCENDING' or 'DESCENDING'")));
        Mockito.when(rules.validatePageSize(any())).thenReturn(Optional.of(new CoreError("PageSize",  "Value should be valid '>=0'")));
        Mockito.when(rules.validatePageNumber(any())).thenReturn(Optional.of(new CoreError("PageNumber",  "Value should be valid '>=0'")));

        Ordering ordering = new Ordering("AAA", "AAA");
        Paging paging = new Paging(-1,-1);

        SearchRequest request = new SearchRequest("pen", "XYZ", ordering, paging);
        List<CoreError> errors = searchValidator.validate(request);
        assertEquals(5, errors.size());
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("Price") && s.getMessage().equals("Field should be valid")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("OrderBy") && s.getMessage().equals("Field value should be valid 'name'/'price'")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("Direction") && s.getMessage().equals("Value should be valid: 'ASCENDING' or 'DESCENDING'")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("PageSize") && s.getMessage().equals("Value should be valid '>=0'")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("PageNumber") && s.getMessage().equals("Value should be valid '>=0'")));
    }
}