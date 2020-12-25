package lesson_8.mysql_jdbc;

import lombok.Data;

@Data
public class User {
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}