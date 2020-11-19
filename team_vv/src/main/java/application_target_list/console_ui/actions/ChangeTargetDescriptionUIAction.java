package application_target_list.console_ui.actions;

import application_target_list.console_ui.UIAction;
import application_target_list.core.requests.ChangeTargetDescriptionRequest;
import application_target_list.core.responses.ChangeTargetDescriptionResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.services.ChangeTargetDescriptionService;

import java.util.Scanner;

public class ChangeTargetDescriptionUIAction implements UIAction {

    private ChangeTargetDescriptionService changeTargetDescriptionService;

    public ChangeTargetDescriptionUIAction(ChangeTargetDescriptionService changeTargetDescriptionService){
        this.changeTargetDescriptionService = changeTargetDescriptionService;
    }

    @Override
    public void execute() {
        while (true) {
            Scanner scr = new Scanner(System.in);

            System.out.print("Enter target ID: ");
            Long targetId = Long.parseLong(scr.nextLine());

            System.out.print("Enter new target description: ");
            String newTargetDescription = scr.nextLine();

            ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(targetId, newTargetDescription);
            ChangeTargetDescriptionResponse response = changeTargetDescriptionService.execute(request);

            if (response.hasErrors()) {
                for (CoreError errors : response.getErrorList()) {
                    System.out.println("Error: " + errors.getField() + " " + errors.getMessage());
                }
            } else {
                System.out.println("----------");
                System.out.println("Target description was changed!");
                System.out.println("----------");
                break;
            }
        }
    }
}
