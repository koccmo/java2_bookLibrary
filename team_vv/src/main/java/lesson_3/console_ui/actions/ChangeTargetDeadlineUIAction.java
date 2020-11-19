package lesson_3.console_ui.actions;

import lesson_3.console_ui.UIAction;
import lesson_3.core.requests.ChangeTargetDeadlineRequest;
import lesson_3.core.responses.ChangeTargetDeadlineResponse;
import lesson_3.core.responses.CoreError;
import lesson_3.core.services.ChangeTargetDeadlineService;

import java.util.Scanner;

public class ChangeTargetDeadlineUIAction implements UIAction {

    private final ChangeTargetDeadlineService changeTargetDeadlineService;

    public ChangeTargetDeadlineUIAction(ChangeTargetDeadlineService changeTargetDeadlineService){
        this.changeTargetDeadlineService = changeTargetDeadlineService;
    }

    @Override
    public void execute() {
        while (true) {
            Scanner scr = new Scanner(System.in);

            System.out.print("Enter target ID: ");
            Long targetId = Long.parseLong(scr.nextLine());

            System.out.print("Enter new target deadline: ");
            int newTargetDeadline = Integer.parseInt(scr.nextLine());

            ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(targetId, newTargetDeadline);
            ChangeTargetDeadlineResponse response = changeTargetDeadlineService.execute(request);

            if (response.hasErrors()) {
                for (CoreError errors : response.getErrorList()) {
                    System.out.println("Error: " + errors.getField() + " " + errors.getMessage());
                }
            } else {
                System.out.println("----------");
                System.out.println("Target deadline was changed!");
                System.out.println("----------");
                break;
            }
        }
    }

}
