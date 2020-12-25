package lesson_8.mysql_jdbc;

import lombok.Data;

@Data
public class ConfigDatabaseParams {
    private String databaseHost;
    private int databasePort;
    private String databaseName;
    private User user;

    public ConfigDatabaseParams(String databaseHost, User user) {
        this.databaseHost = databaseHost;
        this.user = user;
    }

    public ConfigDatabaseParams(String databaseHost, int databasePort, String databaseName) {
        this.databaseHost = databaseHost;
        this.databasePort = databasePort;
        this.databaseName = databaseName;
    }

    public String getConfigConnectDatabase() {
        return "jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + databaseName;
    }

    public String getConfigDatabase() {
        return "jdbc:mysql://" + databaseHost + "/?user=" + user.getName() + "&password=" + user.getPassword();
    }
}