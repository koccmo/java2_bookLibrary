package lesson_4.ui;

import lesson_4.core.requests.FindBooksRequest;
import lesson_4.core.responses.FindBooksResponse;
import lesson_4.core.services.FindBooksService;
import lesson_4.core.services.validators.Ordering;
import lesson_4.core.services.validators.Paging;

import java.util.Scanner;

public class FindBookByAuthorUICommand implements UICommand {

    private final FindBooksService findBooksService;

    public FindBookByAuthorUICommand(FindBooksService findBooksService) {
        this.findBooksService = findBooksService;
    }

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

        FindBooksRequest request = new FindBooksRequest("",bookAuthor, ordering, paging);
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

