package application_target_list.console_ui.actions;

import application_target_list.console_ui.UIAction;
import application_target_list.core.requests.ChangeTargetNameRequest;
import application_target_list.core.responses.ChangeTargetNameResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.services.ChangeTargetNameService;

import java.util.Scanner;

public class ChangeTargetNameUIAction implements UIAction {

    private ChangeTargetNameService changeTargetNameService;

    public ChangeTargetNameUIAction(ChangeTargetNameService changeTargetNameService){
        this.changeTargetNameService = changeTargetNameService;
    }

    @Override
    public void execute() {
        while (true) {
            Scanner scr = new Scanner(System.in);

            System.out.print("Enter target ID: ");
            Long targetId = Long.parseLong(scr.nextLine());

            System.out.print("Enter new target name: ");
            String newTargetName = scr.nextLine();

            ChangeTargetNameRequest request = new ChangeTargetNameRequest(targetId, newTargetName);
            ChangeTargetNameResponse response = changeTargetNameService.execute(request);
            if (response.hasErrors()) {
                for (CoreError errors : response.getErrorList()) {
                    System.out.println("Error: " + errors.getField() + " " + errors.getMessage());
                }
            } else {
                System.out.println("----------");
                System.out.println("Target name was changed!");
                System.out.println("----------");
                break;
            }
        }
    }

}
