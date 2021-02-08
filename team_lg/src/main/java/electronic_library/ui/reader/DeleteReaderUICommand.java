package electronic_library.ui.reader;

import electronic_library.core.requests.reader.DeleteReaderByLastNameRequest;
import electronic_library.core.requests.reader.DeleteReaderRequest;
import electronic_library.core.responses.reader.DeleteReaderByLastNameResponse;
import electronic_library.core.responses.reader.DeleteReaderResponse;
import electronic_library.core.services.reader.DeleteReaderService;
import electronic_library.ui.UICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteReaderUICommand implements UICommand {

    @Autowired
    private DeleteReaderService deleteReaderService;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Deleting reader from Reader's register : ");

        System.out.print("Please enter reader first name : ");
        String readerFirstName = scanner.nextLine();

        System.out.print("Please enter reader last name : ");
        String readerLastName = scanner.nextLine();

        System.out.print("Please enter reader personal code : ");
        String readerPersonalCode = scanner.nextLine();

        DeleteReaderRequest request = new DeleteReaderRequest(readerFirstName, readerLastName, readerPersonalCode);
        DeleteReaderResponse response = deleteReaderService.deleteReaderResponse(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            if (response.isReaderRemoved()) {
                System.out.println("\nReader " + readerFirstName + " " + readerLastName + " with personal code " + readerPersonalCode +" has been successfully deleted from Reader's register in electronic library!");
            } else {
                System.out.println("\nThe reader has not been removed from Reader's register in electronic library.");
            }
        }
    }
}
