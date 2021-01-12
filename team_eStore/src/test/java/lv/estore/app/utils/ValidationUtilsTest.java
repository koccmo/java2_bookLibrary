package lv.estore.app.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationUtilsTest {

    ValidationUtils validationUtils = new ValidationUtils();

    @Test
    public void testIsEmptyField_Valid() {
        boolean isEmpty = validationUtils.isEmptyField("Name");
        assertFalse(isEmpty);
    }

    @Test
    public void testIsEmptyField_Empty() {
        boolean isEmpty = validationUtils.isEmptyField("");
        assertTrue(isEmpty);
    }

    @Test
    public void testIsEmptyField_Absent() {
        boolean isEmpty = validationUtils.isEmptyField(null);
        assertTrue(isEmpty);
    }

    @Test
    public void testIsValidPrice_ValidInteger() {
        boolean isValid = validationUtils.isValidPrice("12");
        assertTrue(isValid);
    }

    @Test
    public void testIsValidPrice_ValidDecimal_1() {
        boolean isValid = validationUtils.isValidPrice("12.0");
        assertTrue(isValid);
    }

    @Test
    public void testIsValidPrice_ValidDecimal_2() {
        boolean isValid = validationUtils.isValidPrice("0.01");
        assertTrue(isValid);
    }

    @Test
    public void testIsValidPrice_ValidDecimal_3() {
        boolean isValid = validationUtils.isValidPrice("0,01");
        assertTrue(isValid);
    }

    @Test
    public void testIsValidPrice_Empty() {
        boolean isValid = validationUtils.isValidPrice("");
        assertFalse(isValid);
    }

    @Test
    public void testIsValidPrice_Absent() {
        boolean isValid = validationUtils.isValidPrice(null);
        assertFalse(isValid);
    }


    @Test
    public void testIsValidId_ValidInteger() {
        boolean isValid = validationUtils.isValidId("1");
        assertTrue(isValid);
    }

    @Test
    public void testIsValidId_validZeroesWithNumber() {
        boolean isValid = validationUtils.isValidId("001");
        assertTrue(isValid);
    }

    @Test
    public void testIsValidId_InvalidNegative() {
        boolean isValid = validationUtils.isValidId("-1");
        assertFalse(isValid);
    }

    @Test
    public void testIsValidId_InvalidZero() {
        boolean isValid = validationUtils.isValidId("0");
        assertFalse(isValid);
    }

    @Test
    public void testIsValidId_InvalidDecimal_1() {
        boolean isValid = validationUtils.isValidId("0.1");
        assertFalse(isValid);
    }

    @Test
    public void testIsValidId_InvalidDecimal_2() {
        boolean isValid = validationUtils.isValidId("0,1");
        assertFalse(isValid);
    }

    @Test
    public void testIsValidId_InvalidString() {
        boolean isValid = validationUtils.isValidId("XYZ");
        assertFalse(isValid);
    }

    @Test
    public void testIsValidId_Empty() {
        boolean isValid = validationUtils.isValidId("");
        assertFalse(isValid);
    }

    @Test
    public void testIsValidId_Absent() {
        boolean isValid = validationUtils.isValidId(null);
        assertFalse(isValid);
    }


    @Test
    public void testIsValidPagingParameters_ValidInteger() {
        boolean isValid = validationUtils.isValidPagingParameters(1);
        assertTrue(isValid);
    }

    @Test
    public void testIsValidPagingParameters_InvalidZero() {
        boolean isValid = validationUtils.isValidPagingParameters(0);
        assertFalse(isValid);
    }

    @Test
    public void testIsValidPagingParameters_InvalidNegative() {
        boolean isValid = validationUtils.isValidPagingParameters(-1);
        assertFalse(isValid);
    }

    @Test
    public void testIsValidPagingParameters_Absent() {
        boolean isValid = validationUtils.isValidPagingParameters(null);
        assertFalse(isValid);
    }

    @Test
    public void testIsValidOrderBy_ValidName() {
        boolean isValid = validationUtils.isValidOrderBy("name");
        assertTrue(isValid);
    }

    @Test
    public void testisValidOrderBy_ValidPrice() {
        boolean isValid = validationUtils.isValidOrderBy("price");
        assertTrue(isValid);
    }

    @Test
    public void testIsValidOrderBy_InvalidAny() {
        boolean isValid = validationUtils.isValidOrderBy("any");
        assertFalse(isValid);
    }

    @Test
    public void testIsValidOrderBy_Empty() {
        boolean isValid = validationUtils.isValidOrderBy("");
        assertFalse(isValid);
    }

    @Test
    public void testIsValidOrderBy_Absent() {
        boolean isValid = validationUtils.isValidOrderBy(null);
        assertFalse(isValid);
    }

    @Test
    public void testisValidDirection_ValidAscending() {
        boolean isValid = validationUtils.isValidDirection("ASCENDING");
        assertTrue(isValid);
    }

    @Test
    public void testIsValidDirection_ValidDescending() {
        boolean isValid = validationUtils.isValidDirection("DESCENDING");
        assertTrue(isValid);
    }

    @Test
    public void testIsValidDirection_Invalid() {
        boolean isValid = validationUtils.isValidDirection("DESC");
        assertFalse(isValid);
    }

    @Test
    public void testIsValidDirection_Empty() {
        boolean isValid = validationUtils.isValidDirection("");
        assertFalse(isValid);
    }

    @Test
    public void testIsValidDirection_Absent() {
        boolean isValid = validationUtils.isValidDirection(null);
        assertFalse(isValid);
    }

}