package team_VK.application.ui;

import team_VK.application.ApplicationContext;
import team_VK.application.ui.GetBooksListUIAction;
import team_VK.application.ui.UIActions;

import java.text.ParseException;
import java.util.Optional;
import java.util.Scanner;

public class BookSearchAndBookMenuUIAction implements UIActions {

    ApplicationContext context;

    public BookSearchAndBookMenuUIAction(ApplicationContext context) {
        this.context = context;
    }

    private boolean contin;

    @Override
    public void execute() throws ParseException {

        contin = true;
        while (contin) {
            showSearchAndBookingMenu();
            switch (userChoiceInSearchAndBookingMenu()) {
                case 1: {
                    GetBooksListUIAction getBooksListUIAction = context.getBean(GetBooksListUIAction.class);
                    getBooksListUIAction.execute();
                    break;
                }

                case 2: {
                    // book search
                    break;
                }

                case 7: {
                    contin = false;
                    break;
                }
            }
        }
    }

    private void showSearchAndBookingMenu() {
        System.out.println("Please select your action.");
        System.out.println("1. Get books list.");
        System.out.println("2. Search book by.");

        System.out.println("7. Exit main menu.");
        System.out.println();

    }

    private int userChoiceInSearchAndBookingMenu() {
        try {
            return userChoiceWithPossibleException();
        } catch (Exception e) {
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
