package java2.application_target_list.console_ui.actions;

import java2.application_target_list.core.requests.ChangeTargetDescriptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.responses.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.services.ChangeTargetDescriptionService;


import java.util.Scanner;

@Component
public class ChangeTargetDescriptionUIAction implements UIAction {

    @Autowired private ChangeTargetDescriptionService changeTargetDescriptionService;
    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true) {

            Long targetId = getIdFromUser();
            String newTargetDescription = getNewDescriptionFromUser();

            ChangeTargetDescriptionRequest request = createRequest(targetId, newTargetDescription);
            ChangeTargetDescriptionResponse response = createResponse(request);

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
        System.out.println("Target description was changed!");
        System.out.println("----------");
    }

    private void printResponseErrors(ChangeTargetDescriptionResponse response){
        response.getErrorList().forEach(System.out::println);
    }

    private ChangeTargetDescriptionResponse createResponse(ChangeTargetDescriptionRequest request){
        return changeTargetDescriptionService.execute(request);
    }

    private ChangeTargetDescriptionRequest createRequest(Long targetId, String newTargetDescription){
        return new ChangeTargetDescriptionRequest(targetId, newTargetDescription);
    }

    private Long getIdFromUser(){
        System.out.print("Enter target ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private String getNewDescriptionFromUser(){
        System.out.print("Enter new target description: ");
        return scr.nextLine();
    }
}
