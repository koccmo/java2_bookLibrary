package internet_store.user_interface.administrator_menu.create_client_menu;

import internet_store.ProductListApplication;
import internet_store.core.domain.Client;
import internet_store.core.request.client.AddClientRequest;
import internet_store.core.request.client.DeleteClientRequest;
import internet_store.core.request.client.UpdateClientRequest;
import internet_store.core.response.client.AddClientResponse;
import internet_store.core.response.client.DeleteClientResponse;
import internet_store.core.response.client.UpdateClientResponse;
import internet_store.core.service.client.*;
import internet_store.user_interface.administrator_menu.create_client_menu.add_client_menu.AddClient;
import internet_store.user_interface.administrator_menu.create_client_menu.delete_client_menu.DeleteClientMenu;
import internet_store.user_interface.administrator_menu.create_client_menu.update_client_menu.UpdateClientMenu;
import internet_store.user_interface.main_menu.MainMenuConsole;

public class ClientMenuConsole {
    private final MainMenuConsole mainMenuConsole;

    public ClientMenuConsole(MainMenuConsole mainMenuConsole) {
        this.mainMenuConsole = mainMenuConsole;
    }

    public void startClientMenuConsole() {
        final ClientMenu clientMenu = ProductListApplication.applicationContext
                .getBean(ClientMenu.class);
        final DeleteClientMenu deleteClientMenu = ProductListApplication.applicationContext
                .getBean(DeleteClientMenu.class);
        final UpdateClientMenu updateClientMenu = ProductListApplication.applicationContext
                .getBean(UpdateClientMenu.class);
        final AddClientService addClientService = ProductListApplication.applicationContext
                .getBean(AddClientService.class);
        final DeleteClientService deleteClientService = ProductListApplication.applicationContext
                .getBean(DeleteClientService.class);
        final UpdateClientService updateClientService = ProductListApplication.applicationContext
                .getBean(UpdateClientService.class);
        final UpdateClientAddNewChangesService updateClientAddNewChangesService = ProductListApplication
                .applicationContext.getBean(UpdateClientAddNewChangesService.class);
        final PrintClientService printClientService = ProductListApplication.applicationContext
                .getBean(PrintClientService.class);
        boolean returnMainMenu = true;
        do {
            clientMenu.showClientMenu();
            int userInput = clientMenu.getUserInput();

            switch (userInput) {
                case 1 -> {
                    Client client = new AddClient().addClient();
                    AddClientRequest clientRequest = new AddClientRequest(client);
                    AddClientResponse response = addClientService.execute(clientRequest);
                    if (!(response.hasErrors())) {
                        System.out.println("Client add to list");
                    } else {
                        response.getErrors().forEach(r -> System.out.println(r.getField() +
                                r.getMessage()));
                    }
                }
                case 2 -> {
                    deleteClientMenu.showMenuDeleteProduct();
                    long deletedId = deleteClientMenu.getUserDeletedClientIdInput();
                    DeleteClientRequest deleteRequest = new DeleteClientRequest(deletedId);
                    DeleteClientResponse response = deleteClientService.execute(deleteRequest);
                    if (!(response.hasErrors())) {
                        System.out.println("Information about client deleted from list");
                    } else {
                        response.getErrors().forEach(r -> System.out.println(r.getField() +
                                r.getMessage()));
                    }
                }
                case 3 -> {
                    updateClientMenu.showMenuUpdateProduct();
                    long updatedId = updateClientMenu.getUserUpdatedClientIdInput();

                    UpdateClientRequest updateRequest = new UpdateClientRequest(updatedId);
                    UpdateClientResponse updateResponse = updateClientService.execute(updateRequest);
                    if (updateResponse.hasErrors()) {
                        updateResponse.getErrors().forEach(r -> System.out.println(r.getField() +
                                r.getMessage()));
                        break;
                    }
                    Client client = new AddClient().addClient();
                    AddClientRequest clientRequest = new AddClientRequest(client);
                    clientRequest.getClient().setId(updatedId);
                    AddClientResponse response = updateClientAddNewChangesService.execute(clientRequest);
                    if (!(response.hasErrors())) {
                        System.out.println("Information about client updated");
                    } else {
                        response.getErrors().forEach(r -> System.out.println(r.getField() +
                                r.getMessage()));
                    }
                }
                case 4 -> printClientService.print();

                case 5 -> returnMainMenu = false;
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (returnMainMenu);
        mainMenuConsole.startMainMenu();
    }
}