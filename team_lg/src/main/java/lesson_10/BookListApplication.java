package lesson_10;

import lesson_10.config.BookListConfiguration;
import lesson_10.ui.ApplicationMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BookListApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = createApplicationContext();
        ApplicationMenu applicationMenu = applicationContext.getBean(ApplicationMenu.class);

        while (true) {
            applicationMenu.printMenu();
            try {
                int menuValue = applicationMenu.getMenuNumberFromUser();
                applicationMenu.executeSelectedMenuItem(menuValue);
            } catch (NumberFormatException e) {
                System.out.println("\nIncorrect input, please enter menu item number:");
            }
        }
    }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(BookListConfiguration.class);
    }
}


