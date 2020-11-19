package application_target_list.console_ui.actions;

import application_target_list.console_ui.UIAction;
import application_target_list.core.requests.ChangeTargetDeadlineRequest;
import application_target_list.core.responses.ChangeTargetDeadlineResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.services.ChangeTargetDeadlineService;

import java.util.Scanner;

public class ChangeTargetDeadlineUIAction implements UIAction {

    private final ChangeTargetDeadlineService changeTargetDeadlineService;

    public ChangeTargetDeadlineUIAction(ChangeTargetDeadlineService changeTargetDeadlineService){
        this.changeTargetDeadlineService = changeTargetDeadlineService;
    }

    @Override
    public void execute() {
        Scanner scr = new Scanner(System.in);

        System.out.print("Enter target ID: ");
        Long targetId = Long.parseLong(scr.nextLine());

        System.out.print("Enter new target deadline: ");
        int newTargetDeadline = Integer.parseInt(scr.nextLine());

        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(targetId, newTargetDeadline);
        ChangeTargetDeadlineResponse response = changeTargetDeadlineService.execute(request);

        if (response.hasErrors()) {
            System.out.println("----------");
            for (CoreError errors : response.getErrorList()) {
                System.out.println("Error: " + errors.getField() + " " + errors.getMessage());
            }
            System.out.println("----------");
        } else {
            System.out.println("----------");
            System.out.println("Target deadline was changed!");
            System.out.println("----------");
        }
    }

}
