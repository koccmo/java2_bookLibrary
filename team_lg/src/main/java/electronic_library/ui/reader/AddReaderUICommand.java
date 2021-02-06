package electronic_library.ui.reader;

import electronic_library.core.requests.reader.AddReaderRequest;
import electronic_library.core.responses.reader.AddReaderResponse;
import electronic_library.core.services.reader.AddReaderService;
import electronic_library.ui.UICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddReaderUICommand implements UICommand {

    @Autowired
    private AddReaderService addReaderService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter reader first name: ");
        String readerFirstName = scanner.nextLine();

        System.out.print("Please enter reader last name: ");
        String readerLastName = scanner.nextLine();

        System.out.print("Please enter reader personal code : ");
        String readerPersonalCode = scanner.nextLine();

        System.out.print("Please enter reader phone number: ");
        String readerPhoneNumber = scanner.nextLine();

        System.out.print("Please enter reader e-mail: ");
        String readerEmail = scanner.nextLine();

        System.out.print("Please enter reader address: ");
        String readerAddress = scanner.nextLine();

        AddReaderRequest request = new AddReaderRequest(readerFirstName, readerLastName, readerPersonalCode, readerPhoneNumber, readerEmail, readerAddress);
        AddReaderResponse response = addReaderService.execute(request);

        if (response.hasErrors()) {
            System.out.println("\n================== INCORRECT DATA ENTRY ======================");
            response.getErrors().forEach(System.out::println);
            System.out.println("==============================================================");
        } else {
            System.out.println("\n==============================================================");
            System.out.println("New reader ID: " + response.getNewReader().getId());
            System.out.println("Reader: " + readerFirstName + " " + readerLastName + ", Personal code: " + readerPersonalCode + ", Phone number: " + readerPhoneNumber + ", E-mail: " + readerEmail + ", Address: " + readerAddress);
            System.out.println("\nThe Reader has been successfully added to Reader's register in electronic library!");
            System.out.println("==============================================================");
        }
    }
}

