package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import java2.application_target_list.core.validators.target.ChangeTargetNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.responses.target.ChangeTargetNameResponse;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Service
@Transactional
public class ChangeTargetNameService {

    @Autowired private JpaTargetRepository jpaTargetRepository;
    @Autowired private ChangeTargetNameValidator validator;

    public ChangeTargetNameResponse execute(ChangeTargetNameRequest request){

        List<CoreError> errors = validator.validate(request);

        if (!jpaTargetRepository.existsById(request.getTargetIdToChange())){
            errors.add(new CoreError("Target ID;","no target with that ID"));
        }

        if (!errors.isEmpty()) {
            return new ChangeTargetNameResponse(errors);
        }

        jpaTargetRepository.changeTargetName(request.getTargetIdToChange(), request.getNewTargetName());
        return new ChangeTargetNameResponse(request.getTargetIdToChange(), request.getNewTargetName());
    }
}
