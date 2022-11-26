package bookLibrary.console.ui;

import bookLibrary.core.request.Ordering;
import bookLibrary.core.request.Paging;
import bookLibrary.core.request.SearchBooksRequest;
import bookLibrary.core.response.SearchBooksResponse;
import bookLibrary.core.service.SearchBooksService;
import bookLibrary.dependency_injection.DIComponent;
import bookLibrary.dependency_injection.DIDependency;

import java.util.Scanner;
@DIComponent
public class SearchBooksUIAction implements UIAction{
    @DIDependency private SearchBooksService searchBooksService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Book Author for Search - ");
        String author = scanner.nextLine();
        System.out.println("Enter Book Title for Search - ");
        String title = scanner.nextLine();
        System.out.println("Enter Book Author or Title - ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter ASCENDING or DESCENDING ");
        String orderDirection = scanner.nextLine();
        System.out.println("Enter Page Number");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Page Size");
        Integer pageSize = Integer.parseInt(scanner.nextLine());
        Ordering ordering = new Ordering(orderBy, orderDirection);
        Paging paging = new Paging(pageNumber, pageSize);
        SearchBooksRequest request = new SearchBooksRequest(author, title, ordering, paging);
        SearchBooksResponse response = searchBooksService.execute(request);
        if (response.hasError()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField()
                    + " " + coreError.getDescription()));
        } else {
            response.getFoundedBookList().forEach(System.out :: println);
        }
    }
}
