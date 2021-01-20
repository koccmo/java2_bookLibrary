package adventure_time;

import adventure_time.configuration.AdminConfiguration;
import adventure_time.ui.AdminMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AdminApplication {

    private static void regularAppWorks(AdminMenu adminMenu) {

        while (true) {

            adminMenu.mainMenu();
            int subjectChoice = adminMenu.getUserChoice();
            adminMenu.executeSelectedMenuItem(adminMenu.getUserChoice());
        }
    }

    public static void main(String[] args) {

        ApplicationContext adminApp = new AnnotationConfigApplicationContext(AdminConfiguration.class);
        AdminMenu adminMenu = adminApp.getBean(AdminMenu.class);

        regularAppWorks(adminMenu);

    }



}
