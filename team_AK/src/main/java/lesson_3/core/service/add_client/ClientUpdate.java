package lesson_3.core.service.add_client;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.domain.Client;
import lesson_3.core.domain.Product;

import java.util.List;

public interface ClientUpdate {
    void execute(List<CoreError> errors, Client client);
}