package lesson_4.ui;

import lesson_4.core.domain.Book;
import lesson_4.core.requests.FindBookByIdRequest;
import lesson_4.core.responses.FindBookByIdResponse;
import lesson_4.core.services.FindBookByIdService;

import java.util.Optional;
import java.util.Scanner;

public class FindBookByIdUICommand implements UICommand {

    private FindBookByIdService findBookByIdService;

    public FindBookByIdUICommand(FindBookByIdService findBookByIdService) {
        this.findBookByIdService = findBookByIdService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter Book ID: ");
        String id = scanner.nextLine();

        FindBookByIdRequest request = new FindBookByIdRequest(id);
        FindBookByIdResponse response = findBookByIdService.findBookByIdResponse(request);

        Optional<Book> findBook = response.getFindBookById();

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getErrorField() + " " + coreError.getErrorMessage()));
        } else {
            if (response.getFindBookById().isEmpty()) {
                System.out.println("\nNo book with ID=" + id + " in Electronic library");
            } else {
                Book book = findBook.get();
                System.out.println("Found book in the Electronic library :");
                System.out.print(book.toString() + "\n");
            }
        }
    }
}
