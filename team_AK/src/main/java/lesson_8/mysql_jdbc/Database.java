package lesson_8.mysql_jdbc;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private final User user;
    @Getter
    private boolean isDriverLoaded;
    @Getter
    private boolean isDatabaseConnected;
    @Getter
    private boolean isConnectionClosed;
    @Getter
    private boolean isDatabaseCreated;
    @Getter
    private boolean isDatabaseDeleted;
    @Getter
    private Connection dbConnection;
    @Getter
    private String databaseName;

    public Database(User user) {
        this.user = user;
    }

    public void setJdbcDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            isDriverLoaded = true;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            isDriverLoaded = false;
        }
    }

    public void connectDatabase(ConfigDatabaseParams params) {
        try {
            dbConnection = DriverManager.getConnection(params.getConfigConnectDatabase(), user.getName(), user.getPassword());
            databaseName = params.getDatabaseName();
            isDatabaseConnected = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isDatabaseConnected = false;
        }
    }

    public void closeConnection() {
        try {
            dbConnection.close();
            isConnectionClosed = true;
        } catch (SQLException e) {
            isConnectionClosed = false;
        }
    }

    public void createDatabase(String databaseName, ConfigDatabaseParams params) {
        try {
            Connection connection = DriverManager.getConnection(params.getConfigDatabase());
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE " + databaseName);
            connection.close();
            isDatabaseCreated = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isDatabaseCreated = false;
        }
    }

    public void deleteDatabase(String databaseName, ConfigDatabaseParams params) {
        Connection connection;
        Statement statement;
        try {
            connection = DriverManager.getConnection(params.getConfigDatabase());
            statement = connection.createStatement();
            statement.executeUpdate("DROP DATABASE " + databaseName);
            connection.close();
            isDatabaseDeleted = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isDatabaseDeleted = false;
        }
    }
}