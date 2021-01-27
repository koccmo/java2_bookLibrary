package java2.application_target_list.console_ui.actions.board;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddRecordUIAction implements UIAction {

    @Autowired AddRecordService addRecordService;
    @Autowired UserRepository userRepository;
    @Autowired TargetRepository targetRepository;
    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {

        while(true){

            if (isTargetOrUserListEmpty()){
                printBreakMessage();
                break;
            }

            Long targetId = getTargetIdFromUser();
            Long userId = getUserIdFromUser();

            AddRecordRequest addRecordRequest = new AddRecordRequest(targetId, userId);
            AddRecordResponse addRecordResponse = addRecordService.execute(addRecordRequest);

            if (addRecordResponse.hasErrors()){
                printResponseErrors(addRecordResponse);
            } else {
                printResponseResultMessage();
                break;
            }
        }
    }

    private void printBreakMessage(){
        System.out.println("----------");
        System.out.println("Targets list or Users list is empty!");
        System.out.println("----------");
    }

    private boolean isTargetOrUserListEmpty(){
        return userRepository.getUsersList().isEmpty() || targetRepository.getTargetsList().isEmpty();
    }

    private Long getTargetIdFromUser(){
        System.out.print("Enter target ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private Long getUserIdFromUser(){
        System.out.print("Enter user ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private void printResponseErrors(AddRecordResponse addRecordResponse){
        addRecordResponse.getErrorList().forEach(System.out::println);
    }
    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("Record was added to list.");
        System.out.println("----------");
    }

}
