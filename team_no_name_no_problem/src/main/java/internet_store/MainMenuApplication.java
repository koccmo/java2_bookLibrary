package internet_store;

import internet_store.config.MainMenuConfiguration;
import internet_store.console_ui.ProgramMenu.ProgramMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainMenuApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = createApplicationContext();
        ProgramMenu programMenu = applicationContext.getBean(ProgramMenu.class);

        System.out.println("\nWelcome to the No Name No Problem Internet Store  ¯\\_(ツ)_/¯");

        while (true) {

            programMenu.printMenu();

            int userSelectedMenuNumber = programMenu.inputValidInteger("Please enter menu number: ");

            programMenu.executeUIAction(userSelectedMenuNumber);
        }

    }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(MainMenuConfiguration.class);
    }
}