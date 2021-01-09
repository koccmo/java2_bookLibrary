package adventure_time;

import adventure_time.configuration.EventConfiguration;
import adventure_time.ui.EventMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EventApplication {

    private static void alwaysOnDesktop(EventMenu eventMenu) {

        while (true) {

            eventMenu.menuForEvents();
            eventMenu.executeSelectedMenuItem(eventMenu.getUserChoice());
        }
    }

    public static void main(String[] args) {

        ApplicationContext eventsApp = new AnnotationConfigApplicationContext(EventConfiguration.class);
        EventMenu eventMenu = eventsApp.getBean(EventMenu.class);

        alwaysOnDesktop(eventMenu);

    }
}