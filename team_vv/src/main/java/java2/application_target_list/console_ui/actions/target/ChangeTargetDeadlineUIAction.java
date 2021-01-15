package java2.application_target_list.console_ui.actions.target;

import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.services.target.ChangeTargetDeadlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.responses.target.ChangeTargetDeadlineResponse;


import java.util.Scanner;

@Component
public class ChangeTargetDeadlineUIAction implements UIAction {

    @Autowired ChangeTargetDeadlineService changeTargetDeadlineService;
    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true) {
            Long targetId = getIdFromUser();
            Integer newTargetDeadline = getNewDeadlineFromUser();

            ChangeTargetDeadlineRequest request = createRequest(targetId, newTargetDeadline);
            ChangeTargetDeadlineResponse response = createResponse(request);

            if (response.hasErrors()) {
                printResponseErrors(response);
            } else {
                printResponseResultMessage();
                break;
            }
        }
    }

    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("Target deadline was changed!");
        System.out.println("----------");
    }

    private void printResponseErrors(ChangeTargetDeadlineResponse response){
        response.getErrorList().forEach(System.out::println);
    }

    private ChangeTargetDeadlineResponse createResponse(ChangeTargetDeadlineRequest request){
        return changeTargetDeadlineService.execute(request);
    }

    private ChangeTargetDeadlineRequest createRequest(Long targetId, Integer newTargetDeadline){
        return new ChangeTargetDeadlineRequest(targetId, newTargetDeadline);
    }

    private Long getIdFromUser(){
        System.out.print("Enter target ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private Integer getNewDeadlineFromUser(){
        System.out.print("Enter new target deadline: ");
       return Integer.parseInt(scr.nextLine());
    }

}
