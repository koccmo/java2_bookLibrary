package estore.core.validation;

import estore.core.requests.Ordering;
import estore.core.requests.Paging;
import estore.core.requests.SearchProductByCategoryRequest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchProductByCategoryValidatorTest {

    private ValidationRules validationRules = new ValidationRules();
    private SearchProductByCategoryValidator validator = new SearchProductByCategoryValidator(validationRules);

    @Test
    public void shouldNotReturnErrorIfProductCategoryIsProvided() {
        SearchProductByCategoryRequest request = new SearchProductByCategoryRequest("Category");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorIfProductCategoryHasDigits() {
        SearchProductByCategoryRequest request = new SearchProductByCategoryRequest("Category1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product category");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldReturnErrorIfProductCategoryHasWhiteSpaces() {
        SearchProductByCategoryRequest request = new SearchProductByCategoryRequest("Category one");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product category");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldReturnErrorIfOrderDirectionIsEmpty() {
        Ordering ordering = new Ordering("price", null);
        Paging paging = new Paging("2", "10");
        SearchProductByCategoryRequest request =
                new SearchProductByCategoryRequest("Category", ordering, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorIfOrderByIsEmpty() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        Paging paging = new Paging("2", "10");
        SearchProductByCategoryRequest request =
                new SearchProductByCategoryRequest("Category", ordering, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorIfOrderByContainsInvalidValue() {
        Ordering ordering = new Ordering("invalidValue", "ASCENDING");
        Paging paging = new Paging("2", "10");
        SearchProductByCategoryRequest request =
                new SearchProductByCategoryRequest("Category", ordering, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must contain 'name' or 'price' only!");
    }

    @Test
    public void shouldReturnErrorIfOrderDirectionContainsInvalidValue() {
        Ordering ordering = new Ordering("price", "invalidValue");
        Paging paging = new Paging("2", "10");
        SearchProductByCategoryRequest request =
                new SearchProductByCategoryRequest("Category", ordering, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must contain 'ASCENDING/asc' or 'DESCENDING/desc' only!");
    }

    @Test
    public void shouldReturnErrorIfPagingPageNumberContainsInvalidValue() {
        Ordering ordering = new Ordering("price", "asc");
        Paging paging = new Paging("Unalowed", "10");
        SearchProductByCategoryRequest request =
                new SearchProductByCategoryRequest("Category", ordering, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Page Number");
        assertEquals(errors.get(0).getMessage(), "Must be positive integer!");
    }

    @Test
    public void shouldReturnErrorIfPagingPageSizeContainsInvalidValue() {
        Ordering ordering = new Ordering("price", "asc");
        Paging paging = new Paging("10", "");
        SearchProductByCategoryRequest request =
                new SearchProductByCategoryRequest("Category", ordering, paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Page Size");
        assertEquals(errors.get(0).getMessage(), "Must be positive integer!");
    }
}