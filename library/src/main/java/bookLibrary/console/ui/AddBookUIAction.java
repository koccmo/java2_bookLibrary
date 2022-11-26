package bookLibrary.console.ui;

import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.response.AddBookResponse;
import bookLibrary.core.service.AddBookService;
import bookLibrary.dependency_injection.DIComponent;
import bookLibrary.dependency_injection.DIDependency;

import java.util.Scanner;
@DIComponent
public class AddBookUIAction implements UIAction{
    @DIDependency private AddBookService addBookService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter author of Book :");
        String author = scanner.nextLine();
        System.out.println("Enter title of Book :");
        String title = scanner.nextLine();
        AddBookRequest request = new AddBookRequest(author,title);
        AddBookResponse response = addBookService.execute(request);
        if (response.hasError()) {
            response.getErrors().forEach(coreError -> System.out.println(coreError.getField() + " "
                    + coreError.getDescription()));
            System.out.println("Your book not been Added!");
        } else {
            System.out.println("New book id - " + response.getBook().getId());
            System.out.println("Book with id " + response.getBook().getId() + " been added!");
        }

    }
}
