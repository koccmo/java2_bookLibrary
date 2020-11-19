package application_target_list.console_ui.actions;

import application_target_list.console_ui.UIAction;
import application_target_list.core.requests.AddTargetRequest;
import application_target_list.core.responses.AddTargetResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.services.AddTargetService;


import java.util.Scanner;

public class AddTargetUIAction implements UIAction {

    private AddTargetService addTargetService;

    public  AddTargetUIAction(AddTargetService addTargetService) {
        this.addTargetService = addTargetService;
    }

    @Override
    public void execute() {

        while (true){
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
                for (CoreError errors : response.getErrorList()) {
                    System.out.println("Error: " + errors.getField() + " " + errors.getMessage());
                }
            } else {
                System.out.println("----------");
                System.out.println("Your target was added to list.");
                System.out.println("----------");
                break;
            }
        }
    }
}
