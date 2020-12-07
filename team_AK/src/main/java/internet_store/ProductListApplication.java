package internet_store;

import dependency.ApplicationContext;
import dependency.DIApplicationContextBuilder;
import internet_store.integration.telegram.InitTelegram;
import internet_store.user_interface.main_menu.MainMenuConsole;

public class ProductListApplication {

    public static final ApplicationContext context = new DIApplicationContextBuilder()
            .build(ProductListApplication.class);

    public static void main(String[] args) {
        InitTelegram initTelegram = context.getBean(InitTelegram.class);
        initTelegram.init();
        MainMenuConsole mainMenuConsole = context.getBean(MainMenuConsole.class);
        mainMenuConsole.startMainMenu();
    }
}