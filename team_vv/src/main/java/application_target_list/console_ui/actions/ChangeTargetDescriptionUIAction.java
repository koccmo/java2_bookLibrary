package application_target_list.console_ui.actions;

import application_target_list.console_ui.UIAction;
import application_target_list.core.requests.ChangeTargetDescriptionRequest;
import application_target_list.core.responses.ChangeTargetDescriptionResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.services.ChangeTargetDescriptionService;
import application_target_list.dependency_injection.DIComponent;
import application_target_list.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class ChangeTargetDescriptionUIAction implements UIAction {

    @DIDependency private ChangeTargetDescriptionService changeTargetDescriptionService;
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
        for (CoreError error : response.getErrorList()) {
            System.out.println("Error: " + error.getField() + " " + error.getMessage());
        }
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
