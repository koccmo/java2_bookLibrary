package lv.estore.app;

import lv.estore.app.config.EStoreConfiguration;
import lv.estore.app.userInterface.MenuUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EStoreApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = createApplicationContext();
        MenuUI menuUI = applicationContext.getBean(MenuUI.class);
        while (true) {
            menuUI.showMenu();
            int menuNumber = menuUI.getMenuNumberFromUser();
            menuUI.executeSelectedMenuItem(menuNumber);
        }
    }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(EStoreConfiguration.class);
    }
}