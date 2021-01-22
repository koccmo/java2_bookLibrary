package adventure_time;

import adventure_time.configuration.AdminConfiguration;
import adventure_time.ui.AdminMenu;
import adventure_time.ui.menus.SubjectMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class AdminApplication {

    private static Map<Integer, SubjectMenu> menuMap;

    private static void regularAppWorks(AdminMenu adminMenu) {

        while (true) {

            adminMenu.mainMenu();
            int category = adminMenu.getUserChoice();
            menuMap.get(category).show();
            int action = category*10 + adminMenu.getUserChoice();
            adminMenu.executeSelectedMenuItem(action);
        }
    }

    public static void main(String[] args) {

        ApplicationContext adminApp = new AnnotationConfigApplicationContext(AdminConfiguration.class);
        AdminMenu adminMenu = adminApp.getBean(AdminMenu.class);

        menuMap = adminMenu.subjectMenuSelect();
        regularAppWorks(adminMenu);

    }
}
