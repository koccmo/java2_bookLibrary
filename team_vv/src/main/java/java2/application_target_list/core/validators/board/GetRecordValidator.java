package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.requests.board.GetRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetRecordValidator {

    public List<CoreError> validate (GetRecordRequest getRecordRequest) {
        List<CoreError> errors = new ArrayList<>();
        validateId(getRecordRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(GetRecordRequest getRecordRequest) {
        return (getRecordRequest.getId() == null)
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }
}
