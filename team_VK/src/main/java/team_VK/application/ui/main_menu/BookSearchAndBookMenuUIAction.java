package team_VK.application.ui.main_menu;

import team_VK.application.core.services.DIDependency;
import team_VK.application.database.DIComponent;
import team_VK.application.ui.search_book_and_make_booking_menu.GetBooksListUIAction;
import team_VK.application.ui.search_book_and_make_booking_menu.SearchBookByManyCriteriaUIAction;
import team_VK.application.ui.search_book_and_make_booking_menu.SearchBookByOneCriteriaUIAction;
import team_VK.application.ui.UIActions;

import java.text.ParseException;
import java.util.Optional;
import java.util.Scanner;

@DIComponent
public class BookSearchAndBookMenuUIAction implements UIActions {

    @DIDependency
    GetBooksListUIAction getBooksListUIAction;
    @DIDependency
    SearchBookByManyCriteriaUIAction searchBookByManyCriteriaUIAction;
    @DIDependency
    SearchBookByOneCriteriaUIAction searchBookByOneCriteriaUIAction;

    private boolean contin;

    @Override
    public void execute() throws ParseException {

        contin = true;
        while (contin) {
            showSearchAndBookingMenu();
            switch (userChoiceInSearchAndBookingMenu()) {
                case 1: {
                    getBooksListUIAction.execute();
                    break;
                }

                case 2: {
                    searchBookByManyCriteriaUIAction.execute();
                    break;
                }
                case 3:{
                    searchBookByOneCriteriaUIAction.execute();
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
        System.out.println("2. Search book by many criteria.");
        System.out.println("3. Search book by one criteria.");

        System.out.println("7. Exit to main menu.");
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
