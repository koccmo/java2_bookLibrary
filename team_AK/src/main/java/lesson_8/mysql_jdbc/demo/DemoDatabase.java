package lesson_8.mysql_jdbc.demo;

import lesson_8.mysql_jdbc.*;

import java.util.Arrays;
import java.util.List;

public class DemoDatabase {
    public static void main(String[] args) {
        User user = new User("user", "user");
        Database database = new Database(user);
        Table table = new Table(database);
        Records records = new Records(database);
        ConfigDatabaseParams configCreateDatabase = new ConfigDatabaseParams("localhost", user);
        ConfigDatabaseParams configDatabaseParams = new ConfigDatabaseParams("localhost",
                3306, "new_database");

        /* Create database */
        database.createDatabase("new_database", configCreateDatabase);
        System.out.println((database.isDatabaseCreated() ? "Database created" : "Database no created"));
        /* Connect to database */
        database.setJdbcDriver();
        database.connectDatabase(configDatabaseParams);
        System.out.println((database.isDatabaseConnected() ? "Database connected" : "Database no connected"));

        /* Create columns */
        /* Create column with auto increment and set primary key*/
        Column id = new Column("id", ColumnType.INT);
        id.setPrimaryKey(true);
        /* Create another columns */
        Column text_col = new Column("text_col", ColumnType.VARCHAR);
        Column double_col = new Column("double_col", ColumnType.DOUBLE);
        Column decimal_col = new Column("decimal_col", ColumnType.DECIMAL);
        decimal_col.setNotNull(true);
        decimal_col.setDefaultValue("100");
        Column float_col = new Column("float_col", ColumnType.FLOAT);
        float_col.setNotNull(true);
        float_col.setDefaultValue("5.50");

        /* Create table */
        table.createTable("new_table", id, text_col, double_col, decimal_col, float_col);
        /* Insert record to table decimal_col and float_col with default value */
        List<String> columns = Arrays.asList("text_col", "double_col");
        records.insertRecord("new_table", columns, "'TEXT'", "155");
        System.out.println((records.isRecordInserted() ? "Record inserted" : "Record error"));

        print(records, "[ Data from database ]");

        records.updateRecord("new_table", "float_col", "650.88", 1);
        records.updateRecord("new_table", "decimal_col", "358", 1);

        print(records, "[ Data in columns 'decimal_col' and 'float_col' id=1 updated ]");

        records.deleteRecord("new_table", 1);
        System.out.println((records.isRecordDeleted() ? "Record deleted" : "Record error"));
        print(records, "[ After deleting ]");

        database.closeConnection();
        System.out.println((database.isDatabaseConnected() ? "Database close connection" : "Database error"));
    }

    private static void print(Records records, String title) {
        List<String> allColumns = Arrays.asList("id", "text_col", "double_col", "decimal_col", "float_col");
        List<String> res = records.readRecord("new_table", allColumns, "1");
        System.out.println(title);
        if (res.size() == 0) {
            System.out.println("No found record");
            return;
        }
        System.out.print("id=" + res.get(0) + " text_col=" + res.get(1) + " double_col=" + res.get(2)
                + " decimal_col=" + res.get(3) + " float_col=" + res.get(4) + "\n");
    }
}