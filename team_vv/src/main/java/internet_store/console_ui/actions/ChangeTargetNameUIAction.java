package internet_store.console_ui.actions;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.ChangeTargetNameRequest;
import internet_store.core.responses.ChangeTargetNameResponse;
import internet_store.core.responses.CoreError;
import internet_store.core.services.ChangeTargetNameService;

import java.util.Scanner;

public class ChangeTargetNameUIAction implements UIAction {

    private ChangeTargetNameService changeTargetNameService;

    public ChangeTargetNameUIAction(ChangeTargetNameService changeTargetNameService){
        this.changeTargetNameService = changeTargetNameService;
    }

    @Override
    public void execute() {
        Scanner scr = new Scanner(System.in);

        System.out.print("Enter target ID: ");
        Long targetId = Long.parseLong(scr.nextLine());

        System.out.print("Enter new target name: ");
        String newTargetName = scr.nextLine();

        ChangeTargetNameRequest request = new ChangeTargetNameRequest(targetId, newTargetName);
        ChangeTargetNameResponse response = changeTargetNameService.execute(request);
        if (response.hasErrors()) {
            System.out.println("----------");
            for (CoreError errors : response.getErrorList()) {
                System.out.println("Error: " + errors.getField() + " " + errors.getMessage());
            }
            System.out.println("----------");
        } else {
            System.out.println("----------");
            System.out.println("Target name was changed!");
            System.out.println("----------");
        }
    }

}
