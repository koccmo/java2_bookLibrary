package internet_store;

import internet_store.integration.telegram.InitTelegram;
import internet_store.user_interface.main_menu.MainMenuConsole;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StoreApplication {
    public static final ApplicationContext context = new
            AnnotationConfigApplicationContext(StoreConfiguration.class);

    public static void main(String[] args) {
        InitTelegram initTelegram = context.getBean(InitTelegram.class);
        initTelegram.init();
        MainMenuConsole mainMenuConsole = context.getBean(MainMenuConsole.class);
        mainMenuConsole.startMainMenu();
    }
}