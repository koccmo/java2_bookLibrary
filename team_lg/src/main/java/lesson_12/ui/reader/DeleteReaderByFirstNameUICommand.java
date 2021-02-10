package lesson_12.ui.reader;

import lesson_12.core.requests.reader.DeleteReaderByFirstNameRequest;
import lesson_12.core.responses.reader.DeleteReaderByFirstNameResponse;
import lesson_12.core.services.reader.DeleteReaderByFirstNameService;
import lesson_12.ui.UICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteReaderByFirstNameUICommand implements UICommand {

    @Autowired
    private DeleteReaderByFirstNameService deleteReaderByFirstNameService;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Deleting reader from Reader's register by first name : ");
        System.out.print("Please enter reader first name : ");
        String readerFirstName = scanner.nextLine();
        DeleteReaderByFirstNameRequest request = new DeleteReaderByFirstNameRequest(readerFirstName);
        DeleteReaderByFirstNameResponse response = deleteReaderByFirstNameService.deleteReaderByFirstNameResponse(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            if (response.isReaderRemoved()) {
                System.out.println("\nReader with first name = " + readerFirstName + " has been successfully deleted from Reader's register in electronic library!");
            } else {
                System.out.println("\nThe reader with first name = " + readerFirstName + " has not been removed from Reader's register in electronic library.");
            }
        }
    }
}
