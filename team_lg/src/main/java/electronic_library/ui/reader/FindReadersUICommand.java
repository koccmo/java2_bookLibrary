package electronic_library.ui.reader;

import electronic_library.core.requests.Ordering;
import electronic_library.core.requests.Paging;
import electronic_library.core.requests.reader.FindReadersRequest;
import electronic_library.core.responses.reader.FindReadersResponse;
import electronic_library.core.services.reader.FindReadersService;
import electronic_library.ui.UICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindReadersUICommand implements UICommand {

    @Autowired
    private FindReadersService findReadersService;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter reader first name: ");
        String readerFirstName = scanner.nextLine();

        System.out.println("Please enter reader last name: ");
        String readerLastName = scanner.nextLine();

        System.out.println("Please enter reader personal code: ");
        String readerPersonalCode = scanner.nextLine();

        System.out.println("Please enter orderBy (firstName||lastName): ");
        String orderBy = scanner.nextLine();

        System.out.println("Please enter orderDirection (ASC||DESC): ");
        String orderDirection = scanner.nextLine();

        Ordering ordering = new Ordering(orderBy, orderDirection);

        System.out.println("Please enter pageNumber: ");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Please enter pageSize: ");
        Integer pageSize = Integer.parseInt(scanner.nextLine());

        Paging paging = new Paging(pageNumber, pageSize);

        FindReadersRequest request = new FindReadersRequest(readerFirstName, readerLastName, readerPersonalCode, ordering, paging);
        FindReadersResponse response = findReadersService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getErrorField() + " " + coreError.getErrorMessage()));
        } else {
            if (!response.getReaders().isEmpty()) {
                System.out.println("\nFound " + response.getReaders().size() + " reader(s) in Reader's register : ");
                response.getReaders().forEach(System.out::println);
            } else {
                System.out.println("\nNo readers found in the Reader's register.");
            }
        }
    }
}
