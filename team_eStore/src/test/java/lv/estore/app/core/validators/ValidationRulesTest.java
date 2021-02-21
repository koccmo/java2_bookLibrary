package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.Ordering;
import lv.estore.app.core.request.products.Paging;
import lv.estore.app.core.validators.common_validation_rules.ValidationRules;
import lv.estore.app.utils.ValidationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ValidationRulesTest {

    @Mock
    ValidationUtils utils;

    @InjectMocks
    ValidationRules rules;

    @Test
    public void testValidateId_EmptyError() {
        Mockito.when(utils.isValidId(any())).thenReturn(true);
        Optional<CoreError> error = rules.validateId(any(),"1");
        assertEquals(Optional.empty(), error);
    }

    @Test
    public void testValidateId_ErrorPresent() {
        Mockito.when(utils.isValidId(any())).thenReturn(false);
        Optional<CoreError> error = rules.validateId("Id", "");
        assertEquals("Id", error.get().getField());
        assertEquals("Value should be valid", error.get().getMessage());
    }


    @Test
    public void testValidateForEmptyField_ErrorAbsent() {
        Mockito.when(utils.isEmptyField(any())).thenReturn(false);
        Optional<CoreError> error = rules.validateTextField(any(),"");
        assertEquals(Optional.empty(), error);
    }

    @Test
    public void testValidateForEmptyField_ErrorPresent() {
        Mockito.when(utils.isEmptyField(any())).thenReturn(true);
        Optional<CoreError> error = rules.validateId("Id", "");
        assertEquals("Id", error.get().getField());
        assertEquals("Field should not be empty", error.get().getMessage());
    }

    @Test
    public void testValidatePrice_ErrorAbsent() {
        Mockito.when(utils.isValidPrice(any())).thenReturn(true);
        Optional<CoreError> error = rules.validatePrice( "1");
        assertEquals(Optional.empty(), error);
    }

    @Test
    public void testValidatePrice_ErrorPresent() {
        Mockito.when(utils.isValidPrice(any())).thenReturn(false);
        Optional<CoreError> error = rules.validatePrice("1");
        assertEquals("productPrice", error.get().getField());
        assertEquals("Field should be valid", error.get().getMessage());
    }

    @Test
    public void testValidateOrderBy_ErrorAbsent() {
        Mockito.when(utils.isValidOrderBy(any())).thenReturn(true);
        Ordering ordering = new Ordering("", "");
        Optional<CoreError> error = rules.validateOrderBy(ordering);
        assertEquals(Optional.empty(), error);
    }

    @Test
    public void testValidateOrderBy_ErrorPresent() {
        Mockito.when(utils.isValidOrderBy(any())).thenReturn(false);
        Ordering ordering = new Ordering("", "");
        Optional<CoreError> error = rules.validateOrderBy(ordering);
        assertEquals("OrderBy", error.get().getField());
        assertEquals("Field value should be valid 'name'/'price'", error.get().getMessage());
    }

    @Test
    public void testValidateDirection_ErrorAbsent() {
        Mockito.when(utils.isValidDirection(any())).thenReturn(true);
        Ordering ordering = new Ordering("", "");
        Optional<CoreError> error = rules.validateDirection(ordering);
        assertEquals(Optional.empty(), error);
    }

    @Test
    public void testValidateDirection_ErrorPresent() {
        Mockito.when(utils.isValidDirection(any())).thenReturn(false);
        Ordering ordering = new Ordering("", "");
        Optional<CoreError> error = rules.validateDirection(ordering);
        assertEquals("Direction", error.get().getField());
        assertEquals("Value should be valid: 'ASCENDING' or 'DESCENDING'", error.get().getMessage());
    }

    @Test
    public void testValidatePageNumber_ErrorAbsent() {
        Mockito.when(utils.isValidPagingParameters(any())).thenReturn(true);
        Paging paging = new Paging(1, 1);
        Optional<CoreError> error = rules.validatePageNumber(paging);
        assertTrue(Optional.empty().equals(error));
    }

    public void testValidatePageNumber_ErrorPresent() {
        Mockito.when(utils.isValidPagingParameters(any())).thenReturn(false);
        Paging paging = new Paging(1, 1);
        Optional<CoreError> error = rules.validatePageNumber(paging);
        assertEquals("PageNumber", error.get().getField());
        assertEquals("Value should be valid (>=0)", error.get().getMessage());
    }

    @Test
    public void testValidatePageSize_ErrorAbsent() {
        Mockito.when(utils.isValidPagingParameters(any())).thenReturn(true);
        Paging paging = new Paging(1, 1);
        Optional<CoreError> error = rules.validatePageSize(paging);
        assertTrue(Optional.empty().equals(error));
    }

    public void testValidatePageSize_ErrorPresent() {
        Mockito.when(utils.isValidPagingParameters(any())).thenReturn(false);
        Paging paging = new Paging(1, 1);
        Optional<CoreError> error = rules.validatePageSize(paging);
        assertEquals("PageSize", error.get().getField());
        assertEquals("Value should be valid (>=0)", error.get().getMessage());
    }
}