package internet_store.console_ui.actions;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.ChangeTargetDescriptionRequest;
import internet_store.core.responses.ChangeTargetDescriptionResponse;
import internet_store.core.responses.CoreError;
import internet_store.core.services.ChangeTargetDescriptionService;

import java.util.Scanner;

public class ChangeTargetDescriptionUIAction implements UIAction {

    private ChangeTargetDescriptionService changeTargetDescriptionService;

    public ChangeTargetDescriptionUIAction(ChangeTargetDescriptionService changeTargetDescriptionService){
        this.changeTargetDescriptionService = changeTargetDescriptionService;
    }

    @Override
    public void execute() {
        Scanner scr = new Scanner(System.in);
        System.out.print("Enter target ID: ");
        Long targetId = Long.parseLong(scr.nextLine());
        System.out.print("Enter new target description: ");
        String newTargetDescription = scr.nextLine();
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(targetId, newTargetDescription);
        ChangeTargetDescriptionResponse response = changeTargetDescriptionService.execute(request);
        if (response.hasErrors()) {
            System.out.println("----------");
            for (CoreError errors : response.getErrorList()) {
                System.out.println("Error: " + errors.getField() + " " + errors.getMessage());
            }
            System.out.println("----------");
        } else {
            System.out.println("----------");
            System.out.println("Target description was changed!");
            System.out.println("----------");
        }
    }
}
