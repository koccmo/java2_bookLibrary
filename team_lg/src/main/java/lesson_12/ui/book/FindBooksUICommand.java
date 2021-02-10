package lesson_12.ui.book;

import lesson_12.core.requests.Ordering;
import lesson_12.core.requests.Paging;
import lesson_12.core.requests.book.FindBooksRequest;
import lesson_12.core.responses.book.FindBooksResponse;
import lesson_12.core.services.book.FindBooksService;
import lesson_12.ui.UICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindBooksUICommand implements UICommand {

    @Autowired
    private FindBooksService findBooksService;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter book title: ");
        String title = scanner.nextLine();

        System.out.println("Please enter book author: ");
        String author = scanner.nextLine();

        System.out.println("Please enter orderBy (title||author): ");
        String orderBy = scanner.nextLine();

        System.out.println("Please enter orderDirection (ASC||DESC): ");
        String orderDirection = scanner.nextLine();

        Ordering ordering = new Ordering(orderBy, orderDirection);

        System.out.println("Please enter pageNumber: ");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Please enter pageSize: ");
        Integer pageSize = Integer.parseInt(scanner.nextLine());

        Paging paging = new Paging(pageNumber, pageSize);

        FindBooksRequest request = new FindBooksRequest(title, author, ordering, paging);
        FindBooksResponse response = findBooksService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getErrorField() + " " + coreError.getErrorMessage()));
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

