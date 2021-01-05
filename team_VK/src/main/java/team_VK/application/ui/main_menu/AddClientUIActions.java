package team_VK.application.ui.main_menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.core.requests.AddClientRequest;
import team_VK.application.core.responses.AddClientResponse;
import team_VK.application.core.services.main_menu_services.AddClientService;
import team_VK.application.ui.UIActions;
import team_VK.application.ui.additional_function.ErrorsPrinter;

import java.util.Scanner;
@Component
public class AddClientUIActions implements UIActions {

    @Autowired
    private AddClientService service;
    @Autowired private ErrorsPrinter errorsPrinter;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter client First Name :");
        String clientFirstName = scanner.nextLine();

        System.out.println("Please enter client Last Name :");
        String clientLastName = scanner.nextLine();

        System.out.println("Please enter client personal code :");
        String clientPersonalCode = scanner.nextLine();

        AddClientRequest request = new AddClientRequest(clientFirstName, clientLastName, clientPersonalCode);
        AddClientResponse response = service.addClient(request);

        if (!response.havesError()) {
            System.out.println("Your are registered in the Library.");
            System.out.println();
        } else {
            errorsPrinter.execute (response);
        }
    }
}

