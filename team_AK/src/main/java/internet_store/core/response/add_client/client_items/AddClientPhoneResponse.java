package internet_store.core.response.add_client.client_items;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class AddClientPhoneResponse extends CoreErrorResponse {
    @Getter
    private String phoneNumber;

    public AddClientPhoneResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddClientPhoneResponse(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
