package internet_store;

import internet_store.integration.telegram.InitTelegram;
import internet_store.user_interface.main_menu.MainMenuConsole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
public class StoreApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StoreApplication.class);

        InitTelegram initTelegram = context.getBean(InitTelegram.class);
        initTelegram.init();
        MainMenuConsole mainMenuConsole = context.getBean(MainMenuConsole.class);
        mainMenuConsole.startMainMenu();
    }
}