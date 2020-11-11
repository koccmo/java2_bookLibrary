package lesson_2.console_ui;


import lesson_2.services.AddTargetService;

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
        int targetDeadline = Integer.parseInt(scr.nextLine());
        addTargetService.execute(targetName, targetDescription, targetDeadline);
        System.out.println("----------");
        System.out.println("Target was added!");
        System.out.println("----------");
    }
}
