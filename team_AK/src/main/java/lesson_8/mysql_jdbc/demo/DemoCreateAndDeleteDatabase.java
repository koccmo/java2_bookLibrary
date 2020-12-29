package lesson_8.mysql_jdbc.demo;

import lesson_8.mysql_jdbc.ConfigDatabaseParams;
import lesson_8.mysql_jdbc.Database;
import lesson_8.mysql_jdbc.User;

public class DemoCreateAndDeleteDatabase {
    public static void main(String[] args) {
        User user = new User("user", "user");
        Database database = new Database(user);
        ConfigDatabaseParams configCreateDatabase = new ConfigDatabaseParams("localhost", user);

        database.createDatabase("new_database", configCreateDatabase);
        System.out.println(database.isDatabaseCreated() ? "Database successfully created" : "Database no created");

        database.deleteDatabase("new_database", configCreateDatabase);
        System.out.println(database.isDatabaseCreated() ? "Database successfully deleted" : "Database no deleted");
    }
}