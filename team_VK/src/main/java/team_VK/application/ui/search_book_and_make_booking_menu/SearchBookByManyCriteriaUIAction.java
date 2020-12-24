package team_VK.application.ui.search_book_and_make_booking_menu;


import team_VK.application.core.requests.SearchBookRequest;
import team_VK.application.core.responses.SearchBookResponse;
import team_VK.application.core.services.DIDependency;
import team_VK.application.core.services.search_book_and_make_booking_menu_services.SearchBookByMANYCriteriaService;
import team_VK.application.database.DIComponent;
import team_VK.application.ui.UIActions;
import team_VK.application.ui.additional_function.ErrorsPrinter;

import java.text.ParseException;
import java.util.Scanner;
@DIComponent
public class SearchBookByManyCriteriaUIAction implements UIActions {
    @DIDependency private SearchBookByMANYCriteriaService service;
    @DIDependency private ErrorsPrinter errorsPrinter;

    @Override
    public void execute() throws ParseException {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter book Title (if you know): ");
        String bookTitle = scanner.nextLine();

        System.out.println("Please enter book Author (if you know): ");
        String bookAuthor = scanner.nextLine();

        System.out.println("Please enter book ID (if you know): ");
        String bookIdString = scanner.nextLine();

        SearchBookRequest request = new SearchBookRequest(bookTitle, bookAuthor, bookIdString);
        SearchBookResponse response = service.searchBook(request);

        if (!response.havesError()) {System.out.println("Please see books list above");}
        else {
            errorsPrinter.execute (response);
        }
    }
}
