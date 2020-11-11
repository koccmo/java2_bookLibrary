package lesson_2.console_ui;

import lesson_2.services.ChangeTargetDescriptionService;

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
        changeTargetDescriptionService.execute(targetId, newTargetDescription);
        System.out.println("----------");
        System.out.println("Target description was changed!");
        System.out.println("----------");
    }
}
