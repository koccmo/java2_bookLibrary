package lesson_4.ui;

import lesson_4.core.requests.DeleteBookByAuthorRequest;
import lesson_4.core.responses.DeleteBookByAuthorResponse;
import lesson_4.core.services.DeleteBookByAuthorService;

import java.util.Scanner;

public class DeleteBookByAuthorUICommand implements UICommand {

    private final DeleteBookByAuthorService deleteBookByAuthorService;

    public DeleteBookByAuthorUICommand(DeleteBookByAuthorService deleteBookByAuthorService) {
        this.deleteBookByAuthorService = deleteBookByAuthorService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deleting book from electronic library by author : ");
        System.out.print("Please enter book author : ");
        String bookAuthor = scanner.nextLine();
        DeleteBookByAuthorRequest request = new DeleteBookByAuthorRequest(bookAuthor);
        DeleteBookByAuthorResponse response = deleteBookByAuthorService.deleteBookByAuthorResponse(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            if (response.isBookRemoved()) {
                System.out.println("\nBook with author = " + bookAuthor + " has been successfully deleted from to electronic library!");
            } else {
                System.out.println("\nThe book with author = " + bookAuthor + " has not been removed from electronic library.");
            }
        }
    }
}
