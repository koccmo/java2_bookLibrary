package lesson_4.ui;

import lesson_4.core.requests.FindBooksRequest;
import lesson_4.core.responses.FindBooksResponse;
import lesson_4.core.services.FindBooksService;
import lesson_4.core.services.validators.Ordering;
import lesson_4.core.services.validators.Paging;

import java.util.Scanner;

public class FindBooksUICommand implements UICommand {

    private final FindBooksService findBooksService;

    public FindBooksUICommand(FindBooksService findBooksService) {
        this.findBooksService = findBooksService;
    }

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

