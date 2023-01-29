package bookLibrary.console.ui;

import bookLibrary.core.request.GetBookIdRequest;
import bookLibrary.core.response.GetBookIdResponse;
import bookLibrary.core.service.GetBookIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class GetBookIdUIAction implements UIAction{
    @Autowired
    private GetBookIdService getBookIdService;
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Book Author");
        String bookAuthor = scanner.nextLine();
        System.out.println("Enter Book Title");
        String bookTitle = scanner.nextLine();
        GetBookIdRequest request = new GetBookIdRequest(bookAuthor, bookTitle);
        GetBookIdResponse response = getBookIdService.execute(request);
        if(response.hasError()) {
            response.getErrors().forEach(System.out :: println);
        } else {
            System.out.println("Book ID - " + response.getBookId());
        }
    }
}
