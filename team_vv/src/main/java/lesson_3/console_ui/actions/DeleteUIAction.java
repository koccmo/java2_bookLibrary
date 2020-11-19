package lesson_3.console_ui.actions;

import lesson_3.console_ui.UIAction;
import lesson_3.core.requests.DeleteTargetRequest;
import lesson_3.core.responses.CoreError;
import lesson_3.core.responses.DeleteTargetResponse;
import lesson_3.core.services.DeleteTargetService;

import java.util.Scanner;

public class DeleteUIAction implements UIAction {

    private DeleteTargetService deleteTargetService;

    public DeleteUIAction(DeleteTargetService deleteTargetService){
        this.deleteTargetService = deleteTargetService;
    }

    @Override
    public void execute() {
        try {
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
            }
        }catch (NumberFormatException ignored){

        }

        }

}
