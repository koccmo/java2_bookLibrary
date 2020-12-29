package book_library.console_ui;

import book_library.Book;
import book_library.core.requests.Ordering;
import book_library.core.requests.SearchBooksRequest;
import book_library.core.responses.SearchBooksResponse;
import book_library.core.services.SearchBooksService;

import java.util.Scanner;

public class SearchBooksUIAction implements UIAction{

    private SearchBooksService searchBooksService;

    public SearchBooksUIAction(SearchBooksService searchBooksService) {
        this.searchBooksService = searchBooksService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book title: ");
        String title = scanner.nextLine();
        System.out.println("Enter book author: ");
        String author = scanner.nextLine();

        System.out.println("Enter orderBy (title||author): ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter orderDirection (ASCENDING||DESCENDING): ");
        String orderDirection = scanner.nextLine();
        Ordering ordering = orderingIsRequested (orderBy, orderDirection);

        SearchBooksRequest request = new SearchBooksRequest(title, author, ordering);
        SearchBooksResponse response = searchBooksService.execute(request);

        if (response.hasErrors()){
            response.getErrors().forEach(System.out::println);
        } else {
            if(response.getBooks().isEmpty()){
                System.out.println("No book with such parameters was found.");
            }
            System.out.println("Book list:");
            System.out.println("***************************************************************************");
            response.getBooks().forEach(System.out::println);
            System.out.println("***************************************************************************");
            System.out.println("Book list end.");
        }
    }

    private Ordering orderingIsRequested (String orderBy, String orderDirection){
        if (orderBy.isEmpty() && orderDirection.isEmpty()){
            return null;
        }
        else return new Ordering(orderBy, orderDirection);
    }
}
