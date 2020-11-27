package internet_store.core.service.add_client;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Client;
import internet_store.database.client_database.InnerClientDatabase;

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
            int updateIndex = clientDatabase.findClientIndex(client.getId());
            clientDatabase.updateClient(updateIndex, client);
        }
    }
}