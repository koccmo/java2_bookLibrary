package lesson_5.ui;

import lesson_5.core.requests.DeleteBookByTitleRequest;
import lesson_5.core.responses.DeleteBookByTitleResponse;
import lesson_5.core.services.DeleteBookByTitleService;

import java.util.Scanner;

public class DeleteBookByTitleUICommand implements UICommand {

    private final DeleteBookByTitleService deleteBookByTitleService;

    public DeleteBookByTitleUICommand(DeleteBookByTitleService deleteBookByTitleService) {
        this.deleteBookByTitleService = deleteBookByTitleService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deleting book from electronic library by title : ");
        System.out.print("Please enter book title : ");
        String bookTitle = scanner.nextLine();
        DeleteBookByTitleRequest request = new DeleteBookByTitleRequest(bookTitle);
        DeleteBookByTitleResponse response = deleteBookByTitleService.deleteBookByTitleResponse(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            if (response.isBookRemoved()) {
                System.out.println("\nBook with title = " + bookTitle + " has been successfully deleted from to electronic library!");
            } else {
                System.out.println("\nThe book with title = " + bookTitle + " has not been removed from electronic library.");
            }
        }
    }
}
