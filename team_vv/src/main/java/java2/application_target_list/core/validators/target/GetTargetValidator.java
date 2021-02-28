package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.GetTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetTargetValidator {

    private List<CoreError> errors;

    public List<CoreError> validate (GetTargetRequest getTargetRequest) {
        errors = new ArrayList<>();
        validateId(getTargetRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(GetTargetRequest request) {
        return (request.getId() == null)
                ? Optional.of(createTargetIdIsEmptyError())
                : Optional.empty();
    }

    private CoreError createTargetIdIsEmptyError(){
        return new CoreError("id", "Must not be empty!");
    }

}
