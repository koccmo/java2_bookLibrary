package lesson_12.ui.book;

import lesson_12.core.requests.book.DeleteBookByTitleRequest;
import lesson_12.core.responses.book.DeleteBookByTitleResponse;
import lesson_12.core.services.book.DeleteBookByTitleService;
import lesson_12.ui.UICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteBookByTitleUICommand implements UICommand {

    @Autowired
    private DeleteBookByTitleService deleteBookByTitleService;

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
