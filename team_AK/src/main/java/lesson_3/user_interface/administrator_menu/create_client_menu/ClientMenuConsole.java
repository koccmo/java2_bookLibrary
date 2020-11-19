package lesson_3.user_interface.administrator_menu.create_client_menu;

import lesson_3.ProductListApplication;
import lesson_3.core.domain.Client;
import lesson_3.core.request.add_client.AddClientRequest;
import lesson_3.core.request.delete_client.DeleteClientRequest;
import lesson_3.core.request.update_client.UpdateClientRequest;
import lesson_3.core.response.add_client.AddClientResponse;
import lesson_3.core.response.delete_client.DeleteClientResponse;
import lesson_3.core.response.update_client.UpdateClientResponse;
import lesson_3.user_interface.administrator_menu.create_client_menu.add_client_menu.AddClient;
import lesson_3.user_interface.administrator_menu.create_client_menu.delete_client_menu.DeleteClientMenu;
import lesson_3.user_interface.administrator_menu.create_client_menu.update_client_menu.UpdateClientMenu;
import lesson_3.user_interface.main_menu.MainMenuConsole;

public class ClientMenuConsole {
    private final ClientMenu clientMenu = new ClientMenu();
    private final DeleteClientMenu deleteClientMenu = new DeleteClientMenu();
    private final UpdateClientMenu updateClientMenu = new UpdateClientMenu();
    private final MainMenuConsole mainMenuConsole;

    public ClientMenuConsole(MainMenuConsole mainMenuConsole) {
        this.mainMenuConsole = mainMenuConsole;
    }

    public void startClientMenuConsole() {
        boolean returnMainMenu = true;
        do {
            clientMenu.showClientMenu();
            int userInput = clientMenu.getUserInput();

            switch (userInput) {
                case 1 -> {
                    Client client = new AddClient().addClient();
                    AddClientRequest clientRequest = new AddClientRequest(client);
                    AddClientResponse response = ProductListApplication.addClientService
                            .execute(clientRequest);
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
                    DeleteClientResponse response = ProductListApplication.deleteClientService
                            .execute(deleteRequest);
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
                    UpdateClientResponse updateResponse = ProductListApplication.updateClientService
                            .execute(updateRequest);

                    if (updateResponse.hasErrors()) {
                        updateResponse.getErrors().forEach(r -> System.out.println(r.getField() +
                                r.getMessage()));
                        break;
                    }

                    Client client = new AddClient().addClient();
                    AddClientRequest clientRequest = new AddClientRequest(client);
                    clientRequest.getClient().setId(updatedId);
                    AddClientResponse response = ProductListApplication.updateClientAddNewChangesService
                            .execute(clientRequest);
                    if (!(response.hasErrors())) {
                        System.out.println("Information about client updated");
                    } else {
                        response.getErrors().forEach(r -> System.out.println(r.getField() +
                                r.getMessage()));
                    }

                }
                case 4 -> ProductListApplication.printClientService.print();
                case 5 -> returnMainMenu = false;
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (returnMainMenu);
        mainMenuConsole.startMainMenu();
    }
}