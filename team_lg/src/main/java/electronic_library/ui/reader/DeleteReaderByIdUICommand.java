package electronic_library.ui.reader;

import electronic_library.core.requests.reader.DeleteReaderByIdRequest;
import electronic_library.core.responses.reader.DeleteReaderByIdResponse;
import electronic_library.core.services.reader.DeleteReaderByIdService;
import electronic_library.ui.UICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteReaderByIdUICommand implements UICommand {

    @Autowired
    private DeleteReaderByIdService deleteReaderByIdService;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter reader ID : ");
        Long id = scanner.nextLong();
        DeleteReaderByIdRequest request = new DeleteReaderByIdRequest(id);
        DeleteReaderByIdResponse response = deleteReaderByIdService.deleteReaderByIdResponse(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            if (response.isReaderRemoved()) {
                System.out.println("\nReader with ID=" + id + " has been successfully removed from Reader's register in electronic library!");
            } else {
                System.out.println("\nSorry, reader with ID=" + id + " has not been removed from Reader's register in electronic library.");
            }
        }
    }
}
