package team_VK.application.ui;

import team_VK.application.core.services.AddClientService;


import java.util.Scanner;

public class AddClientUIActions implements UIActions {

    private final AddClientService addClientService;

    public AddClientUIActions(AddClientService addClientService) {
        this.addClientService = addClientService;
    }


        @Override
        public void execute () {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter client Name :");
            String clientName = scanner.nextLine();
            addClientService.addClient(clientName);
            System.out.println("Your are registered in the Library.");
            System.out.println();
        }
    }

