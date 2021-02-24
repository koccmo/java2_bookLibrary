package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.SetRecordCompleteDateResponse;
import java2.application_target_list.core.validators.board.SetRecordCompleteDateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//@Component
@Service
@Transactional
public class SetRecordCompleteDateService {

    @Autowired private SetRecordCompleteDateValidator setRecordCompleteDateValidator;
    @Autowired private JpaBoardRepository jpaBoardRepository;

    public SetRecordCompleteDateResponse execute(SetRecordCompleteDateRequest setRecordCompleteDateRequest){

        List<CoreError> errors = setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest);

        if (!jpaBoardRepository.existsById(setRecordCompleteDateRequest.getRecordIdToSetCompleteDate())){
            errors.add(new CoreError("Record ID","no record with that ID"));
        }

        if (!errors.isEmpty()){
            return new SetRecordCompleteDateResponse(errors);
        }

        jpaBoardRepository.setRecordCompleteDate(setRecordCompleteDateRequest.getRecordIdToSetCompleteDate(), getDate());
        return new SetRecordCompleteDateResponse(setRecordCompleteDateRequest.getRecordIdToSetCompleteDate());
    }

    private String getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(myFormatDate);
    }
}
