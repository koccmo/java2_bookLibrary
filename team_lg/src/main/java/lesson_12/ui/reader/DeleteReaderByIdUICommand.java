package lesson_12.ui.reader;

import lesson_12.core.requests.reader.DeleteReaderByIdRequest;
import lesson_12.core.responses.reader.DeleteReaderByIdResponse;
import lesson_12.core.services.reader.DeleteReaderByIdService;
import lesson_12.ui.UICommand;
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
