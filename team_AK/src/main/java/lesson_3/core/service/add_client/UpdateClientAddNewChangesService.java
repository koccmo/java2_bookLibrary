package lesson_3.core.service.add_client;

import lesson_3.ProductListApplication;
import lesson_3.core.core_error.CoreError;
import lesson_3.core.domain.Client;
import lesson_3.database.client_database.InnerClientDatabase;

import java.util.List;

public class UpdateClientAddNewChangesService extends AddClientService implements ClientUpdate {
    private final InnerClientDatabase clientDatabase;

    public UpdateClientAddNewChangesService(InnerClientDatabase clientDatabase) {
        super(clientDatabase);
        this.clientDatabase = clientDatabase;
    }

    @Override
    public void execute(List<CoreError> errors, Client client) {
        if (errors.isEmpty()) {
            int updateIndex = ProductListApplication.findClientService.findClientIndex(clientDatabase.getAllClients(),
                    client.getId());
            clientDatabase.updateClient(updateIndex, client);
        }
    }
}