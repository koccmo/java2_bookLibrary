package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.DeleteRecordResponse;
import java2.application_target_list.core.validators.board.DeleteRecordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeleteRecordService {

    @Autowired
    private DeleteRecordValidator deleteRecordValidator;
    @Autowired
    private JpaBoardRepository jpaBoardRepository;

    private List<CoreError> errors;

    public DeleteRecordResponse execute(DeleteRecordRequest deleteRecordRequest){
        errors = checkDeleteRecordRequestForErrors(deleteRecordRequest);
        checkAvailabilityInDB(deleteRecordRequest);

        if (deleteRecordRequestHaveErrors()){
            return createDeleteRecordResponseWithErrors();
        }

        deleteRecordFromDB(deleteRecordRequest);
        return createDeleteRecordResponse(deleteRecordRequest);
    }

    private DeleteRecordResponse createDeleteRecordResponse(DeleteRecordRequest deleteRecordRequest) {
        return new DeleteRecordResponse(deleteRecordRequest.getRecordIdToDelete());
    }

    private void deleteRecordFromDB(DeleteRecordRequest deleteRecordRequest) {
        jpaBoardRepository.deleteById(deleteRecordRequest.getRecordIdToDelete());;
    }

    private DeleteRecordResponse createDeleteRecordResponseWithErrors() {
        return new DeleteRecordResponse(errors);
    }

    private boolean deleteRecordRequestHaveErrors(){
        return !errors.isEmpty();
    }

    private void checkAvailabilityInDB(DeleteRecordRequest deleteRecordRequest){
        if (recordDoesNotExistInDB(deleteRecordRequest)){
            errors.add(createRecordDoesNotExistError());
        }
    }

    private CoreError createRecordDoesNotExistError() {
        return new CoreError("Record ID","no record with that ID");
    }

    private boolean recordDoesNotExistInDB(DeleteRecordRequest deleteRecordRequest){
        return !jpaBoardRepository.existsById(deleteRecordRequest.getRecordIdToDelete());
    }

    private List<CoreError> checkDeleteRecordRequestForErrors(DeleteRecordRequest deleteRecordRequest){
        return deleteRecordValidator.validate(deleteRecordRequest);
    }
}
