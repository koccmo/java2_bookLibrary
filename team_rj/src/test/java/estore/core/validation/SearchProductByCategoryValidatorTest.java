package estore.core.validation;

import estore.core.requests.Ordering;
import estore.core.requests.SearchProductByCategoryRequest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchProductByCategoryValidatorTest {

    private SearchProductByCategoryValidator validator = new SearchProductByCategoryValidator();

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
        SearchProductByCategoryRequest request =
                new SearchProductByCategoryRequest("Category", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorIfOrderByIsEmpty() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        SearchProductByCategoryRequest request =
                new SearchProductByCategoryRequest("Category", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorIfOrderByContainsInvalidValue() {
        Ordering ordering = new Ordering("invalidValue", "ASCENDING");
        SearchProductByCategoryRequest request =
                new SearchProductByCategoryRequest("Category", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must contain 'name' or 'price' only!");
    }

    @Test
    public void shouldReturnErrorIfOrderDirectionContainsInvalidValue() {
        Ordering ordering = new Ordering("price", "invalidValue");
        SearchProductByCategoryRequest request =
                new SearchProductByCategoryRequest("Category", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must contain 'ASCENDING/asc' or 'DESCENDING/desc' only!");
    }
}