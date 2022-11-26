package bookLibrary.console.ui;

import bookLibrary.core.request.DeleteBookRequest;
import bookLibrary.core.response.DeleteBookResponse;
import bookLibrary.core.service.DeleteBookService;
import bookLibrary.dependency_injection.DIComponent;
import bookLibrary.dependency_injection.DIDependency;

import java.util.Scanner;
@DIComponent
public class DeleteBookUIAction implements UIAction{
    @DIDependency private DeleteBookService deleteBookService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID of Book :");
        var id = scanner.nextLine();
        DeleteBookRequest deleteBookRequest = new DeleteBookRequest(id);
        DeleteBookResponse response = deleteBookService.execute(deleteBookRequest);
        if(response.hasError()) {
            response.getErrors().forEach(coreError -> System.out.println(coreError.getField() + " "
                    + coreError.getDescription()));
        } else {
            if(response.isBookDeleted()) {
                System.out.println("Book was Deleted!");
            } else {
                System.out.println("Book not Deleted!");
            }
        }

    }
}
