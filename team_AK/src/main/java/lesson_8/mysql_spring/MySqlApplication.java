package lesson_8.mysql_spring;

import lesson_8.mysql_spring.console.MainConsole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MySqlApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MySqlApplication.class);

        MainConsole mainMenuConsole = context.getBean(MainConsole.class);
        mainMenuConsole.startMainMenu();
    }
}