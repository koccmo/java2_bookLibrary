package lesson_8.mysql_jdbc;

import lombok.Getter;
import lombok.Setter;

public class Column {
    private final ColumnType columnType;
    private final String columnName;
    private String varcharSize = "155";
    @Getter
    @Setter
    private String defaultValue = "";
    private boolean notNull;
    private boolean primaryKey;

    public Column(String columnName, ColumnType columnType) {
        this.columnType = columnType;
        this.columnName = columnName;
    }

    public void setVarcharSize(String varcharSize) {
        this.varcharSize = varcharSize;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public String toString() {
        String column = (columnType.toString().equals("VARCHAR")) ? "VARCHAR" + "(" + varcharSize + ")"
                : columnType.toString();

        if (isPrimaryKey()) {
            column += " AUTO_INCREMENT PRIMARY KEY,";
            return columnName + " " + column;
        }
        column += (isNotNull()) ? " NULL DEFAULT " + defaultValue + "," : " NULL,";
        return columnName + " " + column;
    }
}