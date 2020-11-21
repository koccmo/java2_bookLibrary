package application_target_list.console_ui.actions;

import application_target_list.console_ui.UIAction;
import application_target_list.core.requests.DeleteTargetRequest;
import application_target_list.core.responses.CoreError;
import application_target_list.core.responses.DeleteTargetResponse;
import application_target_list.core.services.DeleteTargetService;

import java.util.Scanner;

public class DeleteUIAction implements UIAction {

    private final DeleteTargetService deleteTargetService;
    private final Scanner scr = new Scanner(System.in);

    public DeleteUIAction(DeleteTargetService deleteTargetService){
        this.deleteTargetService = deleteTargetService;
    }

    @Override
    public void execute() {
        while (true) {
            Long targetId = getIdFromUser();

            DeleteTargetRequest request = createRequest(targetId);
            DeleteTargetResponse response = createResponse(request);

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
        System.out.println("Target was deleted!");
        System.out.println("----------");
    }

    private void printResponseErrors(DeleteTargetResponse response){
        for (CoreError error : response.getErrorList()) {
            System.out.println("Error: " + error.getField() + " " + error.getMessage());
        }
    }

    private DeleteTargetResponse createResponse(DeleteTargetRequest request){
        return deleteTargetService.execute(request);
    }

    private DeleteTargetRequest createRequest(Long targetId){
        return new DeleteTargetRequest(targetId);
    }

    private Long getIdFromUser(){
        System.out.print("Enter target ID: ");
        return Long.parseLong(scr.nextLine());
    }

}
