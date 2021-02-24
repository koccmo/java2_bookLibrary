package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.DeleteRecordResponse;
import java2.application_target_list.core.validators.board.DeleteRecordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Service
@Transactional
public class DeleteRecordService {

    @Autowired private DeleteRecordValidator deleteRecordValidator;
    @Autowired private JpaBoardRepository jpaBoardRepository;

    public DeleteRecordResponse execute(DeleteRecordRequest deleteRecordRequest){
        List<CoreError> errors = deleteRecordValidator.validate(deleteRecordRequest);

        if (!jpaBoardRepository.existsById(deleteRecordRequest.getRecordIdToDelete())){
            errors.add(new CoreError("Record ID","no record with that ID"));
        }

        if (!errors.isEmpty()){
            return new DeleteRecordResponse(errors);
        }

        jpaBoardRepository.deleteById(deleteRecordRequest.getRecordIdToDelete());
        return new DeleteRecordResponse(deleteRecordRequest.getRecordIdToDelete());
    }
}
