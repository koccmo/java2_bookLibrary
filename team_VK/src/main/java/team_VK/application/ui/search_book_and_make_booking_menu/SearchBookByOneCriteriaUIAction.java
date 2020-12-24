package team_VK.application.ui.search_book_and_make_booking_menu;

import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.BookSearchRequest;
import team_VK.application.core.responses.BookSearchResponse;
import team_VK.application.core.services.search_book_and_make_booking_menu_services.SearchBookByONECriteriaService;
import team_VK.application.core.services.DIDependency;
import team_VK.application.database.DIComponent;
import team_VK.application.ui.UIActions;
import team_VK.application.ui.additional_function.ErrorsPrinter;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@DIComponent
public class SearchBookByOneCriteriaUIAction implements UIActions {

    @DIDependency
    private SearchBookByONECriteriaService searchBookByOneCriteriaService;
    @DIDependency private ErrorsPrinter errorsPrinter;

    @Override
    public void execute() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        List<Book> finalList;
        String criteriaValue = null;

        showSearchMenu();
        int searchCriteria = userChoice();
        switch (searchCriteria) {

            case 1:
                System.out.println("Please enter Book Title");
                criteriaValue = scanner.nextLine();
                break;
            case 2:
                System.out.println("Please enter Book Author");
                criteriaValue = scanner.nextLine();
                break;
            case 3:
                System.out.println("Please enter Book ID");
                criteriaValue = scanner.nextLine();
                break;
        }
        BookSearchRequest request = new BookSearchRequest(searchCriteria, criteriaValue);
        BookSearchResponse response = searchBookByOneCriteriaService.bookSearch(request);

        if (!response.havesError()) {System.out.println("Please see the book above");}
        else {
            errorsPrinter.execute (response);
        }
    }


    private void showSearchMenu() {
        System.out.println("Please select your search criteria.");
        System.out.println("1. By Title.");
        System.out.println("2. By Author.");
        System.out.println("3. By ID.");

        System.out.println("7. Exit main menu.");
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

//
//    private Long inputID() {
//        try {
//            return inputIdWithPossibleException();
//        } catch (Exception e) {
//            return 0L;
//        }
//    }
//
//    private static Long inputIdWithPossibleException() throws NumberFormatException {
//        Scanner scanner = new Scanner(System.in);
//        String choiceString = scanner.nextLine();
//        Optional<Long> choice = Optional.of(Long.valueOf(choiceString));
//        if (choice.isPresent())
//            return choice.get();
//        else return 0L;
//    }
//


}

