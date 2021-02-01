package internet_store.user_interface.administrator_menu.create_client_menu;

import internet_store.core.domain.Client;
import internet_store.core.request.client.AddClientRequest;
import internet_store.core.request.client.DeleteClientRequest;
import internet_store.core.request.client.UpdateClientRequest;
import internet_store.core.response.client.AddClientResponse;
import internet_store.core.response.client.DeleteClientResponse;
import internet_store.core.response.client.UpdateClientResponse;
import internet_store.core.service.client.*;
import internet_store.database.client_database.ClientDatabaseImpl;
import internet_store.user_interface.administrator_menu.create_client_menu.add_client_menu.AddClient;
import internet_store.user_interface.administrator_menu.create_client_menu.delete_client_menu.DeleteClientMenu;
import internet_store.user_interface.administrator_menu.create_client_menu.update_client_menu.UpdateClientMenu;
import internet_store.user_interface.main_menu.MainMenuConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMenuConsole {
    @Autowired
    MainMenuConsole mainMenuConsole;
    @Autowired
    ClientMenu clientMenu;
    @Autowired
    DeleteClientMenu deleteClientMenu;
    @Autowired
    UpdateClientMenu updateClientMenu;
    @Autowired
    AddClientService addClientService;
    @Autowired
    DeleteClientService deleteClientService;
    @Autowired
    UpdateClientService updateClientService;
    @Autowired
    UpdateClientAddNewChangesService updateClientAddNewChangesService;
    @Autowired
    PrintClientService printClientService;
    @Autowired
    ClientDatabaseImpl clientDatabase;

    public void startClientMenuConsole() {
        boolean returnMainMenu = true;
        do {
            clientMenu.showClientMenu();
            int userInput = clientMenu.getUserInput();

            switch (userInput) {
                case 1 -> {
                    Client client = new AddClient().addClient();
                    AddClientRequest clientRequest = new AddClientRequest(client, clientDatabase);
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
                    DeleteClientRequest deleteRequest = new DeleteClientRequest(deletedId, clientDatabase, new Client());
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
                    AddClientRequest clientRequest = new AddClientRequest(client, clientDatabase);
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