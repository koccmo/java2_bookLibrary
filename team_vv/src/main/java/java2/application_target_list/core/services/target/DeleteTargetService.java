package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import java2.application_target_list.core.validators.target.DeleteTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.DeleteTargetResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Service
@Transactional
public class DeleteTargetService {

    @Autowired private DeleteTargetValidator validator;
    @Autowired private JpaTargetRepository jpaTargetRepository;

    public DeleteTargetResponse execute(DeleteTargetRequest request){

        List<CoreError> errors = validator.validate(request);

        if (!jpaTargetRepository.existsById(request.getTargetIdToDelete())){
            errors.add(new CoreError("Target ID;","no target with that ID"));
        }

        if (!errors.isEmpty()) {
            return new DeleteTargetResponse(errors);
        }

        jpaTargetRepository.deleteById(request.getTargetIdToDelete());
        return new DeleteTargetResponse(request.getTargetIdToDelete());
    }

}
