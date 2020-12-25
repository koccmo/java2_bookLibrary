package lesson_8;

import lesson_8.mysql_jdbc.Column;
import lesson_8.mysql_jdbc.ColumnType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColumnTest {
    @Test
    public void createColumnIntNotNull() {
        Column column = new Column("id", ColumnType.INT);
        column.setNotNull(true);
        column.setDefaultValue("55");
        String result = column.toString();
        assertEquals("id INT NULL DEFAULT 55,", result);
    }

    @Test
    public void createColumnInt() {
        Column column = new Column("id", ColumnType.INT);
        column.setNotNull(false);
        String result = column.toString();
        assertEquals("id INT NULL,", result);
    }

    @Test
    public void createColumnInt_2() {
        Column column = new Column("id", ColumnType.INT);
        String result = column.toString();
        assertEquals("id INT NULL,", result);
    }

    @Test
    public void createColumnVarchar() {
        Column column = new Column("text", ColumnType.VARCHAR);
        String result = column.toString();
        assertEquals("text VARCHAR(155) NULL,", result);
    }

    @Test
    public void createColumnVarcharWithSize() {
        Column column = new Column("text", ColumnType.VARCHAR);
        column.setVarcharSize("40");
        String result = column.toString();
        assertEquals("text VARCHAR(40) NULL,", result);
    }

    @Test
    public void createColumnVarcharNotNull() {
        Column column = new Column("text", ColumnType.VARCHAR);
        column.setNotNull(true);
        column.setDefaultValue("x");
        String result = column.toString();
        assertEquals("text VARCHAR(155) NULL DEFAULT x,", result);
    }

    @Test
    public void createColumnVarcharNotNullAndSize() {
        Column column = new Column("text", ColumnType.VARCHAR);
        column.setVarcharSize("400");
        column.setNotNull(true);
        column.setDefaultValue("x");
        String result = column.toString();
        assertEquals("text VARCHAR(400) NULL DEFAULT x,", result);
    }
}