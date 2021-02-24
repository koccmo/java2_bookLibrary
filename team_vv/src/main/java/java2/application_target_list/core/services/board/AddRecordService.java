package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.validators.board.AddRecordValidator;
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
public class AddRecordService {

    @Autowired private AddRecordValidator addRecordValidator;
    @Autowired private JpaBoardRepository jpaBoardRepository;
    @Autowired private JpaTargetRepository jpaTargetRepository;
    @Autowired private JpaUserRepository jpaUserRepository;

    public AddRecordResponse execute(AddRecordRequest addRecordRequest){
        List<CoreError> errors = addRecordValidator.validate(addRecordRequest);

        if (!isTargetIdIDB(addRecordRequest)){
            errors.add(new CoreError("Target ID","no target with that ID!"));
        }

        if (!isUserIdIDB(addRecordRequest)){
            errors.add(new CoreError("User ID","no user with that ID!"));
        }

        if (!errors.isEmpty()){
            return new AddRecordResponse(errors);
        }

        Record record = new Record(addRecordRequest.getTargetId(), addRecordRequest.getUserId(), getDate());
        jpaBoardRepository.save(record);

        return new AddRecordResponse(record);
    }

    private String getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(myFormatDate);
    }

    private boolean isTargetIdIDB(AddRecordRequest request){
        return jpaTargetRepository.existsById(request.getTargetId());
    }

    private boolean isUserIdIDB(AddRecordRequest request){
        return jpaUserRepository.existsById(request.getUserId());
    }
}
