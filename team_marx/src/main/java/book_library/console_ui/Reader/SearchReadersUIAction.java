package book_library.console_ui.Reader;

import book_library.console_ui.UIAction;
import book_library.core.requests.Ordering;
import book_library.core.requests.Paging;
import book_library.core.requests.Reader.SearchReaderRequest;
import book_library.core.responses.Reader.SearchReadersResponse;
import book_library.core.services.Reader.SearchReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchReadersUIAction implements UIAction {

    @Autowired
    private SearchReadersService searchReadersService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter reader first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter reader last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter reader personal code: ");
        String personalCodeString = scanner.nextLine();
        Long personalCode = null;
        if (personalCodeString.isEmpty()) {
            personalCode = null;
        } else {
            personalCode = Long.parseLong(personalCodeString);
        }

        System.out.println("Enter orderBy (firstName||lastName||personalCode): ");
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

        SearchReaderRequest request = new SearchReaderRequest(firstName, lastName, personalCode, ordering, paging);
        SearchReadersResponse response = searchReadersService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            if (response.getReaders().isEmpty()) {
                System.out.println("No reader with such parameters was found.");
            }
            System.out.println("Reader list:");
            System.out.println("***************************************************************************");
            response.getReaders().forEach(System.out::println);
            System.out.println("***************************************************************************");
            System.out.println("Reader list end.");
        }
    }
}
