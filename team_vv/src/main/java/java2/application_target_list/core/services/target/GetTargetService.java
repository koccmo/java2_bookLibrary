package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.requests.target.GetTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.GetTargetResponse;
import java2.application_target_list.core.validators.target.GetTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetTargetService {

    @Autowired private TargetRepository targetRepository;
    @Autowired private GetTargetValidator validator;

    public GetTargetResponse execute(GetTargetRequest getTargetRequest) {
        List<CoreError> errors = validator.validate(getTargetRequest);
        if (!errors.isEmpty()){
            return new GetTargetResponse(errors);
        }

        return targetRepository.getById(getTargetRequest.getId())
                .map(GetTargetResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new GetTargetResponse(errors);});
        }
    }


