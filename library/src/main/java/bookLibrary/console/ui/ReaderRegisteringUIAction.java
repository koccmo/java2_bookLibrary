package bookLibrary.console.ui;

import bookLibrary.core.request.ReaderRegisteringRequest;
import bookLibrary.core.response.ReaderRegisteringResponse;
import bookLibrary.core.service.ReaderRegisteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class ReaderRegisteringUIAction implements UIAction{
    @Autowired
    private ReaderRegisteringService readerRegisteringService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Reader name for registering!");
        String name = scanner.nextLine();
        System.out.println("Enter Reader lastname for registering!");
        String lastName = scanner.nextLine();
        System.out.println("Enter Reader personal code!");
        String personalCode = scanner.nextLine();
        ReaderRegisteringRequest request = new ReaderRegisteringRequest(name, lastName, personalCode);
        ReaderRegisteringResponse response = readerRegisteringService.execute(request);
        if(response.hasError()) {
            response.getErrors().forEach(coreError -> System.out.println(coreError.getField() + " " +
                    coreError.getDescription()));
            System.out.println("Reader not been Registered!!!");
        } else {
            System.out.println("Reader been Registered!!!!");
        }
    }
}
