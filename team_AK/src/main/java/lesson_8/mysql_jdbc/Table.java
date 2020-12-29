package lesson_8.mysql_jdbc;

import lombok.Getter;

import java.sql.SQLException;

public class Table {
    private final Database database;
    @Getter
    private boolean isTableCreated;

    public Table(Database database) {
        this.database = database;
    }

    public void createTable(String tableName, Column... column) {
        StringBuilder allColumns = new StringBuilder();
        for (Column c : column) {
            allColumns.append(c.toString());
        }
        String columns = "CREATE TABLE "  + tableName + " (" + allColumns
                .substring(0, allColumns.length() - 1) + ")";
        try {
            database.getDbConnection().createStatement().executeUpdate(columns);
            isTableCreated = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isTableCreated = false;
        }
    }
}