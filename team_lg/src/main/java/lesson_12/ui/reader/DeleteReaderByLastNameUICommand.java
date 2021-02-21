package lesson_12.ui.reader;

import lesson_12.core.requests.reader.DeleteReaderByLastNameRequest;
import lesson_12.core.responses.reader.DeleteReaderByLastNameResponse;
import lesson_12.core.services.reader.DeleteReaderByLastNameService;
import lesson_12.ui.UICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteReaderByLastNameUICommand implements UICommand {

    @Autowired
    private DeleteReaderByLastNameService deleteReaderByLastNameService;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Deleting reader from Reader's register by last name : ");
        System.out.print("Please enter reader last name : ");
        String readerLastName = scanner.nextLine();
        DeleteReaderByLastNameRequest request = new DeleteReaderByLastNameRequest(readerLastName);
        DeleteReaderByLastNameResponse response = deleteReaderByLastNameService.deleteReaderByLastNameResponse(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            if (response.isReaderRemoved()) {
                System.out.println("\nReader with last name = " + readerLastName + " has been successfully deleted from Reader's register in electronic library!");
            } else {
                System.out.println("\nThe reader with last name = " + readerLastName + " has not been removed from Reader's register in electronic library.");
            }
        }
    }
}
