package internet_store.core.service.add_client;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Client;

import java.util.List;

public interface ClientUpdate {
    void execute(List<CoreError> errors, Client client);
}