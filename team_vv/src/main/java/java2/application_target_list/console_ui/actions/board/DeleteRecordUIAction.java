package java2.application_target_list.console_ui.actions.board;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.responses.board.DeleteRecordResponse;
import java2.application_target_list.core.services.board.DeleteRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteRecordUIAction implements UIAction {

    @Autowired DeleteRecordService deleteRecordService;
    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while(true) {
            Long recordId = getIdFromUser();

            DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(recordId);
            DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);

            if (deleteRecordResponse.hasErrors()) {
                printResponseErrors(deleteRecordResponse);
            } else {
                printResponseResultMessage();
                break;
            }
        }
    }

    private Long getIdFromUser(){
        System.out.print("Enter record ID: ");
        return Long.parseLong(scr.nextLine());
    }
    private void printResponseErrors(DeleteRecordResponse deleteRecordResponse){
        deleteRecordResponse.getErrorList().forEach(System.out::println);
    }

    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("Record was deleted!");
        System.out.println("----------");
    }

}
