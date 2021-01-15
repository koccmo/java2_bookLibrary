package lv.estore.app;

import lv.estore.app.config.EStoreConfiguration;
import lv.estore.app.userInterface.MenuUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EStoreApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(EStoreConfiguration.class);

        MenuUI menu = applicationContext.getBean(MenuUI.class);
        while (true) {
            menu.showMenu();
            int menuNumber = menu.getMenuNumberFromUser();
            menu.executeSelectedMenuItem(menuNumber);
        }
    }
}