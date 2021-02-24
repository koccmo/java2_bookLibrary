package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.GetTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.GetTargetResponse;
import java2.application_target_list.core.validators.target.GetTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Service
@Transactional
public class GetTargetService {

    @Autowired private GetTargetValidator validator;
    @Autowired private JpaTargetRepository jpaTargetRepository;

    public GetTargetResponse execute(GetTargetRequest getTargetRequest) {
        List<CoreError> errors = validator.validate(getTargetRequest);
        if (!errors.isEmpty()){
            return new GetTargetResponse(errors);
        }

        return jpaTargetRepository.findById(getTargetRequest.getId())
                .map(GetTargetResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new GetTargetResponse(errors);});
        }
    }


