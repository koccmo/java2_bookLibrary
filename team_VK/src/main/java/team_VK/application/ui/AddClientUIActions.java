package team_VK.application.ui;

import team_VK.application.core.requests.AddClientRequest;
import team_VK.application.core.responses.AddClientResponse;
import team_VK.application.core.services.AddClientService;
import team_VK.application.core.services.DIDependency;
import team_VK.application.database.DIComponent;


import java.util.Scanner;
@DIComponent
public class AddClientUIActions implements UIActions {

    @DIDependency
    private AddClientService service;

//    public AddClientUIActions(AddClientService service) {
//        this.service = service;
//    }


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
            System.out.println("Your request rejected. \n");
            System.out.println(response.errorList.toString());
        }
    }
}

