package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.GetTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.GetTargetResponse;
import java2.application_target_list.core.validators.target.GetTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetTargetService {

    @Autowired
    private GetTargetValidator validator;
    @Autowired
    private JpaTargetRepository jpaTargetRepository;

    private List<CoreError> errors;

    public GetTargetResponse execute(GetTargetRequest getTargetRequest) {
        errors = checkRequestForErrors(getTargetRequest);

        if (requestHaveErrors()){
            return createGetTargetResponseWithErrors();
        }

        return createGetTargetResponse(getTargetRequest);
    }

    private GetTargetResponse createGetTargetResponseWithErrors() {
        return new GetTargetResponse(errors);
    }

    private GetTargetResponse createGetTargetResponse(GetTargetRequest getTargetRequest) {
        return jpaTargetRepository.findById(getTargetRequest.getId())
                .map(GetTargetResponse::new)
                .orElseGet(() -> {
                    errors.add(createTargetDoesNotExistError());
                    return new GetTargetResponse(errors);});
    }

    private CoreError createTargetDoesNotExistError() {
        return new CoreError("id", "Not found!");
    }

    private boolean requestHaveErrors(){
        return !errors.isEmpty();
    }

    private List<CoreError> checkRequestForErrors(GetTargetRequest getTargetRequest){
        return validator.validate(getTargetRequest);
    }
}


