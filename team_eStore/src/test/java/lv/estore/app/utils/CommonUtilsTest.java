package lv.estore.app.utils;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CommonUtilsTest {

    CommonUtils commonUtils = new CommonUtils();

    @Test
    public void testValidValue_1() {
        BigDecimal result = commonUtils.createBigDecimal("17.3");
        assertEquals("17.30", result.toPlainString());
    }

    @Test
    public void testValidValue_2() {
        BigDecimal result = commonUtils.createBigDecimal("1");
        assertEquals("1.00", result.toPlainString());
    }

    @Test
    public void testValidValue_3() {
        BigDecimal result = commonUtils.createBigDecimal("00001.00000001");
        assertEquals("1.00", result.toPlainString());
    }

    @Test
    public void testValidValue_4() {
        BigDecimal result = commonUtils.createBigDecimal("1,01");
        assertEquals("1.01", result.toPlainString());
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidValue_XYZ(){
        BigDecimal result = commonUtils.createBigDecimal("XYZ");
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidValue_Empty(){
        BigDecimal result = commonUtils.createBigDecimal("");
    }

    @Test(expected = NullPointerException.class)
    public void testInvalidValue_Absent(){
        BigDecimal result = commonUtils.createBigDecimal(null);
    }
}