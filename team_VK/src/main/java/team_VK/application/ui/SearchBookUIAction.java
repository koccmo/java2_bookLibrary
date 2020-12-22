package team_VK.application.ui;


import team_VK.application.core.requests.SearchBookRequest;
import team_VK.application.core.responses.SearchBookResponse;
import team_VK.application.core.services.DIDependency;
import team_VK.application.core.services.SearchBookService;

import java.text.ParseException;
import java.util.Scanner;

public class SearchBookUIAction implements  UIActions {
    @DIDependency private SearchBookService service;

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
            response.getErrorList().forEach(coreError -> System.out.println(coreError.toString()));
        }


    }
}
