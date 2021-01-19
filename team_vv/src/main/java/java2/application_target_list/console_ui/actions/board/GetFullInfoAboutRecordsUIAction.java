package java2.application_target_list.console_ui.actions.board;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.GetFullInfoAboutRecordsRequest;
import java2.application_target_list.core.responses.board.GetFullInfoAboutRecordsResponse;
import java2.application_target_list.core.services.board.GetFullInfoAboutRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetFullInfoAboutRecordsUIAction implements UIAction {

    @Autowired
    GetFullInfoAboutRecordsService getFullInfoAboutRecordsService;

    @Override
    public void execute() {
    GetFullInfoAboutRecordsRequest getFullInfoAboutRecordsRequest = new GetFullInfoAboutRecordsRequest();
    GetFullInfoAboutRecordsResponse getFullInfoAboutRecordsResponse = getFullInfoAboutRecordsService.execute(getFullInfoAboutRecordsRequest);

        if (isRecordListEmpty(getFullInfoAboutRecordsResponse)){
        printResponseMessage();
         } else {
        printRecordsList(getFullInfoAboutRecordsResponse);
    }
}

    private boolean isRecordListEmpty(GetFullInfoAboutRecordsResponse getFullInfoAboutRecordsResponse){
        return getFullInfoAboutRecordsResponse.getRecordList().isEmpty();
    }

    private void printResponseMessage(){
        System.out.println("----------");
        System.out.println("Board is empty");
        System.out.println("----------");
    }

    private void printRecordsList(GetFullInfoAboutRecordsResponse getFullInfoAboutRecordsResponse) {
        System.out.println("ID. [Target name]  [Target description] [Target deadline] [User first name; User Last name] [Record Added Date] [Record Complete Date]");
        for (Record record : getFullInfoAboutRecordsResponse.getRecordList()){
            System.out.println(record.getRecordId() + ". ["
                    + record.getTargetName() + "] [" + record.getTargetDescription() + "] [" + record.getTargetDeadline() + "] ["
                    + record.getUserFirstName() + " " + record.getUserLastName() + "] ["
                    + record.getDateAdded() + "] [" + record.getDateComplete() + "]");
        }
        System.out.println("----------");
    }
}
