package book_library.console_ui.Book;
import book_library.console_ui.UIAction;
import book_library.core.requests.Ordering;
import book_library.core.requests.Paging;
import book_library.core.requests.Book.SearchBooksRequest;
import book_library.core.responses.Book.SearchBooksResponse;
import book_library.core.services.Book.SearchBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchBooksUIAction implements UIAction {

    @Autowired
    private SearchBooksService searchBooksService;

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
        if (orderBy.isEmpty()) {
            orderBy = null;
        }
        if (orderDirection.isEmpty()) {
            orderDirection = null;
        }

        System.out.println("Enter pageNumber: ");
        String pageNumberString = scanner.nextLine();
        System.out.println("Enter pageSize: ");
        String pageSizeString = scanner.nextLine();
        Integer pageNumber = null;
        Integer pageSize = null;
        if (pageNumberString.isEmpty()) {
            pageNumber = null;
        } else {
            pageNumber = Integer.parseInt(pageNumberString);
        }

        if (pageSizeString.isEmpty()) {
            pageSize = null;
        } else {
            pageSize = Integer.parseInt(pageSizeString);
        }

        Ordering ordering = new Ordering(orderBy, orderDirection);
        Paging paging = new Paging(pageNumber, pageSize);

        SearchBooksRequest request = new SearchBooksRequest(title, author, ordering, paging);
        SearchBooksResponse response = searchBooksService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            if (response.getBooks().isEmpty()) {
                System.out.println("No book with such parameters was found.");
            }
            System.out.println("Book list:");
            System.out.println("***************************************************************************");
            response.getBooks().forEach(System.out::println);
            System.out.println("***************************************************************************");
            System.out.println("Book list end.");
        }
    }
}
