package internet_store.core.service.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.client.AddClientRequest;

import java.util.List;

public interface ClientUpdate {
    void execute(List<CoreError> errors, AddClientRequest request);
}