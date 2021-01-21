package adventure_time;

import adventure_time.configuration.AdminConfiguration;
import adventure_time.ui.AdminMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EventApplication {

    private static void alwaysOnDesktop(AdminMenu adminMenu) {

        while (true) {

            //eventMenu.mainMenu();

            adminMenu.menuForEvents();
            adminMenu.executeSelectedMenuItem(adminMenu.getUserChoice());
        }
    }

    public static void main(String[] args) {

        ApplicationContext eventsApp = new AnnotationConfigApplicationContext(AdminConfiguration.class);
        AdminMenu adminMenu = eventsApp.getBean(AdminMenu.class);

        alwaysOnDesktop(adminMenu);

    }
}