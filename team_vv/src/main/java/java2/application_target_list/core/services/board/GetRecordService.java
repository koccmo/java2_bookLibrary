package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.GetRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.GetRecordResponse;
import java2.application_target_list.core.validators.board.GetRecordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetRecordService {

    @Autowired
    private GetRecordValidator getRecordValidator;
    @Autowired
    private JpaBoardRepository jpaBoardRepository;

    private List<CoreError> errors;

    public GetRecordResponse execute(GetRecordRequest getRecordRequest){
        errors = checkRequestForErrors(getRecordRequest);

        if (requestHaveErrors()) {
            createResponseWithErrors();
        }

        return jpaBoardRepository.findById(getRecordRequest.getId()).map(GetRecordResponse::new)
                .orElseGet(() -> {
                    errors.add(createRecordDOesNotExistError());
                    return new GetRecordResponse(errors);});
    }

    private CoreError createRecordDOesNotExistError(){
        return new CoreError("id", "Not found");
    }

    private GetRecordResponse createResponseWithErrors() {
        return new GetRecordResponse(errors);
    }

    private boolean requestHaveErrors(){
        return !errors.isEmpty();
    }

    private List<CoreError> checkRequestForErrors(GetRecordRequest getRecordRequest){
        return getRecordValidator.validate(getRecordRequest);
    }

}
