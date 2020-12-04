package application_target_list.console_ui.actions;

import application_target_list.console_ui.UIAction;
import application_target_list.core.requests.ChangeTargetNameRequest;
import application_target_list.core.responses.ChangeTargetNameResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.services.ChangeTargetNameService;
import application_target_list.dependency_injection.DIComponent;
import application_target_list.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class ChangeTargetNameUIAction implements UIAction {

    @DIDependency private ChangeTargetNameService changeTargetNameService;
    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true) {
            Long targetId = getIdFromUser();
            String newTargetName = getNewNameFromUser();

            ChangeTargetNameRequest request = createRequest(targetId, newTargetName);
            ChangeTargetNameResponse response = createResponse(request);

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
        System.out.println("Target name was changed!");
        System.out.println("----------");
    }

    private void printResponseErrors(ChangeTargetNameResponse response){
        for (CoreError error : response.getErrorList()) {
            System.out.println("Error: " + error.getField() + " " + error.getMessage());
        }
    }

    private ChangeTargetNameResponse createResponse(ChangeTargetNameRequest request){
        return changeTargetNameService.execute(request);
    }

    private ChangeTargetNameRequest createRequest(Long targetId, String newTargetName){
        return new ChangeTargetNameRequest(targetId, newTargetName);
    }

    private Long getIdFromUser(){
        System.out.print("Enter target ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private String getNewNameFromUser(){
        System.out.print("Enter new target name: ");
        return scr.nextLine();
    }

}
