package application_target_list.console_ui.actions;

import application_target_list.console_ui.UIAction;
import application_target_list.core.requests.DeleteTargetRequest;
import application_target_list.core.responses.CoreError;
import application_target_list.core.responses.DeleteTargetResponse;
import application_target_list.core.services.DeleteTargetService;

import java.util.Scanner;

public class DeleteUIAction implements UIAction {

    private DeleteTargetService deleteTargetService;

    public DeleteUIAction(DeleteTargetService deleteTargetService){
        this.deleteTargetService = deleteTargetService;
    }

    @Override
    public void execute() {
        while (true) {
            Scanner scr = new Scanner(System.in);

            System.out.print("Enter target ID: ");
            Long targetId = Long.parseLong(scr.nextLine());

            DeleteTargetRequest request = new DeleteTargetRequest(targetId);
            DeleteTargetResponse response = deleteTargetService.execute(request);

            if (response.hasErrors()) {
                for (CoreError errors : response.getErrorList()) {
                    System.out.println("Error: " + errors.getField() + " " + errors.getMessage());
                }
            } else {
                System.out.println("----------");
                System.out.println("Target was deleted!");
                System.out.println("----------");
                break;
            }
        }
    }

}
