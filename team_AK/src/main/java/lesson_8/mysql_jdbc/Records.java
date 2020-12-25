package lesson_8.mysql_jdbc;

import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Records {
    private final Database database;
    @Getter
    private boolean isRecordInserted;
    @Getter
    private boolean isRecordRead;
    @Getter
    private boolean isRecordDeleted;
    @Getter
    private boolean isRecordUpdated;

    public Records(Database database) {
        this.database = database;
    }

    public void insertRecord(String tableName, List<String> columns, String... values) {
        StringBuilder allValues = new StringBuilder();
        for (String value : values) {
            allValues.append(value).append(",");
        }
        String allColumns = prepareColumnsList(columns);

        String record = "INSERT INTO " + tableName + " (" + allColumns + ")" +
                " VALUES(" + allValues.substring(0, allValues.length() - 1) + ")";

        try {
            database.getDbConnection().createStatement().executeUpdate(record);
            isRecordInserted = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isRecordInserted = false;
        }

    }

    public List<String> readRecord(String tableName, List<String> columns, String recordIdOrAll) {
        List<String> recordData = new ArrayList<>();
        String readRecord;
        String allColumns = prepareColumnsList(columns);

        if (recordIdOrAll.equals("all")) {
            readRecord = "SELECT " + allColumns + " FROM " + tableName;
        } else {
            readRecord = "SELECT " + allColumns + " FROM " + tableName + " WHERE id=" + recordIdOrAll;
        }

        try {
            ResultSet result = database.getDbConnection().createStatement().executeQuery(readRecord);
            while (result.next()) {
                columns.forEach(c -> {
                    try {
                        recordData.add(result.getString(c));
                    } catch (SQLException e) {
                        isRecordRead = false;
                        System.out.println(e.getMessage());
                    }
                });
            }
            isRecordRead = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isRecordRead = false;
        }
        return recordData;
    }

    public void updateRecord(String tableName, String column, String newValue, int recordId) {

        String record = "UPDATE " + tableName + " SET " + column + "=" + newValue + " WHERE id=" + recordId;

        try {
            database.getDbConnection().createStatement().executeUpdate(record);
            isRecordUpdated = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isRecordUpdated = false;
        }
    }

    public void deleteRecord(String tableName, int recordId) {

        String record = "DELETE FROM " + tableName + " WHERE id=" + recordId;

        try {
            database.getDbConnection().createStatement().executeUpdate(record);
            isRecordDeleted = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isRecordDeleted = false;
        }
    }

    private String prepareColumnsList(List<String> columns) {
        StringBuilder columnsList = new StringBuilder();
        for (String column : columns) {
            columnsList.append(column).append(",");
        }
        return columnsList.substring(0, columnsList.length() - 1);
    }
}