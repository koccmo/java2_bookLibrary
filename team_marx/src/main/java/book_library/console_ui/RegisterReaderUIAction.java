package book_library.console_ui;

import book_library.core.requests.RegisterReaderRequest;
import book_library.core.responses.RegisterReaderResponse;
import book_library.core.services.RegisterReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RegisterReaderUIAction implements UIAction{

    @Autowired
    private RegisterReaderService registerReaderService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter reader first name: ");
        String readerFirstName = scanner.nextLine();
        System.out.println("Enter reader last name: ");
        String readerLastName = scanner.nextLine();
        RegisterReaderRequest request = new RegisterReaderRequest(readerFirstName, readerLastName);
        RegisterReaderResponse response = registerReaderService.execute(request);

        if (response.hasErrors()){
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Reader was added to the list.");
            System.out.println("New reader id is: " + response.getNewReader().getId());
        }
    }
}
