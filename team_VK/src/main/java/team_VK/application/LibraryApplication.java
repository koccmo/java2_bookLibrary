package team_VK.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import team_VK.application.configuration.SpringCoreConfiguration;
import team_VK.application.console_vi.ProgramMenu;
import team_VK.application.core.services.additional_functions.DataBaseFillAdditionalFunction;
import team_VK.application.web_ui.config.SpringWebConfiguration;

import java.text.ParseException;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class LibraryApplication {


    public static void main(String[] args) throws ParseException {

        ConfigurableApplicationContext context = SpringApplication.run(SpringWebConfiguration.class);

        DataBaseFillAdditionalFunction   dataBaseFillAdditionalFunction =
                context.getBean(DataBaseFillAdditionalFunction.class);
        dataBaseFillAdditionalFunction.execute();

        ProgramMenu programMenu = context.getBean(ProgramMenu.class);
        while (true) {
            programMenu.print();
            int menuNumber = programMenu.userChoiceWithPossibleException();
            programMenu.executeSelectedMenuItem(menuNumber);
        }



    }

    private static ApplicationContext getApplicationContext() {
        return new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
    }

//
//    private static void executeUserChoice(int menuPointNumber) throws ParseException {
//        switch (menuPointNumber) {
//            case 1: {
//                AddBookUIAction addBookUIAction = context.getBean(AddBookUIAction.class);
//                addBookUIAction.execute();
//                break;
//            }
//
//            case 2: {
//                RemoveBookUIAction removeBookUIAction = context.getBean(RemoveBookUIAction.class);
//                removeBookUIAction.execute();
//                break;
//            }
//            case 3: {
//                BookSearchAndBookMenuUIAction bookSearchAndBookMenuUIAction = context.getBean(BookSearchAndBookMenuUIAction.class);
//                bookSearchAndBookMenuUIAction.execute();
//                break;
//            }
//            case 4:
//                ShowBookUIActions showBookUIActions = context.getBean(ShowBookUIActions.class);
//                showBookUIActions.execute();
//                break;
//            case 5:
//                BookBookUIAction bookBookUIAction = context.getBean(BookBookUIAction.class);
//                bookBookUIAction.execute();
//                break;
//            case 6:
//                AddClientUIActions addClientUIActions = context.getBean(AddClientUIActions.class);
//                addClientUIActions.execute();
//                break;
//            case 7: {
//                ExitProgramUIAction exitProgramUIAction = context.getBean(ExitProgramUIAction.class);
//                exitProgramUIAction.execute();
//                break;
//            }
//        }
//    }
//
//
//    private static void showUserMenu() {
//        System.out.println("Please select your action.");
//        System.out.println("1. Add new book.");
//        System.out.println("2. Delete book.");
//        System.out.println("3. Search book and make booking.");
//        System.out.println("4. Show book parameters by ID.");
//        System.out.println("5. Book book.");
//        System.out.println("6. Register new Client.");
//        System.out.println("7. Exit program.");
//        System.out.println();
//
//    }
//
//    private static int userChoice() {
//        try {
//            return userChoiceWithPossibleException();
//        } catch (Exception e) {
//            return 0;
//        }
//    }
//
//    private static int userChoiceWithPossibleException() throws NumberFormatException {
//        Scanner scanner = new Scanner(System.in);
//        String choiceString = scanner.nextLine();
//        Optional<Integer> choice = Optional.of(Integer.valueOf(choiceString));
//        if (choice.isPresent())
//            return choice.get();
//        else return 0;
//    }


}

