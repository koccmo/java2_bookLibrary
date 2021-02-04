package electronic_library.ui.book;

import electronic_library.core.requests.book.FindBooksRequest;
import electronic_library.core.requests.Ordering;
import electronic_library.core.requests.Paging;
import electronic_library.core.responses.book.FindBooksResponse;
import electronic_library.core.services.book.FindBooksService;
import electronic_library.ui.UICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindBookByAuthorUICommand implements UICommand {

    @Autowired
    private FindBooksService findBooksService;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your book author: ");
        String bookAuthor = scanner.nextLine();

        System.out.println("Enter orderBy (title||author): ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter orderDirection (ASC||DESC): ");
        String orderDirection = scanner.nextLine();
        Ordering ordering = new Ordering(orderBy, orderDirection);

        System.out.println("Please enter your page number: ");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Please enter your page size: ");
        Integer pageSize = Integer.parseInt(scanner.nextLine());

        Paging paging = new Paging(pageNumber, pageSize);

        FindBooksRequest request = new FindBooksRequest("", bookAuthor, ordering, paging);
        FindBooksResponse response = findBooksService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            if (!response.getBooks().isEmpty()) {
                System.out.println("\nFound " + response.getBooks().size() + " book(s) in Electronic library : ");
                response.getBooks().forEach(System.out::println);
            } else {
                System.out.println("\nNo books found in the Electronic library.");
            }
        }
    }
}

