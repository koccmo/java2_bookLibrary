package lesson_3.console_ui.actions;

import lesson_3.console_ui.UIAction;
import lesson_3.core.requests.AddTargetRequest;
import lesson_3.core.responses.AddTargetResponse;
import lesson_3.core.responses.CoreError;
import lesson_3.core.services.AddTargetService;

import java.util.Scanner;

public class AddTargetUIAction implements UIAction {

    private AddTargetService addTargetService;

    public  AddTargetUIAction(AddTargetService addTargetService) {
        this.addTargetService = addTargetService;
    }

    @Override
    public void execute() {

            Scanner scr = new Scanner(System.in);

            System.out.print("Enter target name: ");
            String targetName = scr.nextLine();

            System.out.print("Enter target description: ");
            String targetDescription = scr.nextLine();

            System.out.print("Enter target deadline(days): ");
            Integer targetDeadline = Integer.parseInt(scr.nextLine());


            AddTargetRequest request = new AddTargetRequest(targetName, targetDescription, targetDeadline);
            AddTargetResponse response = addTargetService.execute(request);


            if (response.hasErrors()) {
                System.out.println("----------");
                for (CoreError errors : response.getErrorList()) {
                    System.out.println("Error: " + errors.getField() + " " + errors.getMessage());
                }
                System.out.println("----------");
            } else {
                System.out.println("----------");
                System.out.println("Your target was added to list.");
                System.out.println("----------");
            }
    }
}
