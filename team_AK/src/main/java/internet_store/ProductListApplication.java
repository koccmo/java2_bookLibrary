package internet_store;

import internet_store.integration.telegram.InitTelegram;
import internet_store.user_interface.main_menu.MainMenuConsole;

public class ProductListApplication {
    public static ApplicationContext applicationContext = new ApplicationContext();

    public static void main(String[] args) {
        InitTelegram initTelegram = applicationContext.getBean(InitTelegram.class);
        initTelegram.init();
        MainMenuConsole mainMenuConsole = applicationContext.getBean(MainMenuConsole.class);
        mainMenuConsole.startMainMenu();
    }
}