package team_VK.application;


import team_VK.application.database.DataBaseFiller;
import team_VK.application.dependenci_injection.DIApplicationContextBuilder;
import team_VK.application.ui.main_menu.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;
import java.util.Scanner;

public class LibraryApplication {

    public static void main(String[] args) throws ParseException {

        try {
            ApplicationContext context = new DIApplicationContextBuilder().build("team_VK.application");
            DataBaseFiller dataBaseFiller = context.getBean(DataBaseFiller.class);
            dataBaseFiller.fill();
         //   BookSearchAndBookMenuUIAction bookSearchAndBookMenuUIAction = new BookSearchAndBookMenuUIAction(context);
            while (true) {
                showUserMenu();
                switch (userChoice()) {
                    case 1: {
                        AddBookUIAction addBookUIAction = context.getBean(AddBookUIAction.class);
                        addBookUIAction.execute();
                        break;
                    }

                    case 2: {
                        RemoveBookUIAction removeBookUIAction = context.getBean(RemoveBookUIAction.class);
                        removeBookUIAction.execute();
                        break;
                    }
                    case 3: {
                        BookSearchAndBookMenuUIAction bookSearchAndBookMenuUIAction = context.getBean(BookSearchAndBookMenuUIAction.class);
                        bookSearchAndBookMenuUIAction.execute();
                        break;
                    }
//                case 4:
//                    ShowBookUIActions showBookUIActions = context.getBean(ShowBookUIActions.class);
//                    showBookUIActions.execute();
//                    break;
                    case 5:
                        BookBookUIAction bookBookUIAction = context.getBean(BookBookUIAction.class);
                        bookBookUIAction.execute();
                        break;
                    case 6:
                        AddClientUIActions addClientUIActions = context.getBean(AddClientUIActions.class);
                        addClientUIActions.execute();
                        break;
                    case 7: {
                        ExitProgramUIAction exitProgramUIAction = context.getBean(ExitProgramUIAction.class);
                        exitProgramUIAction.execute();
                        break;
                    }
                }
            }
        }
        catch (IOException e) {new RuntimeException("Classes not found");}
        catch (ClassNotFoundException e) {new RuntimeException("Classes not found");}
    }


    private static void showUserMenu() {
        System.out.println("Please select your action.");
        System.out.println("1. Add new book.");
        System.out.println("2. Delete book.");
        System.out.println("3. Search book and make booking.");
        //   System.out.println("4. Show book parameters by ID.");
        System.out.println("5. Book book.");
        System.out.println("6. Register new Client.");
        System.out.println("7. Exit program.");
        System.out.println();

    }

    private static int userChoice() {
        try {
            return userChoiceWithPossibleException();
        } catch (Exception e){
            return 0;
        }
    }

    private static int userChoiceWithPossibleException() throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        String choiceString = scanner.nextLine();
        Optional<Integer> choice = Optional.of(Integer.valueOf(choiceString));
        if (choice.isPresent())
            return choice.get();
        else return 0;
    }


}
