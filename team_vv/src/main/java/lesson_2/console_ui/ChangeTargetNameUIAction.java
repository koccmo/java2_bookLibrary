package lesson_2.console_ui;

import lesson_2.services.ChangeTargetNameService;

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
        Long targetId =  Long.parseLong(scr.nextLine());
        System.out.print("Enter new target name: ");
        String newTargetName = scr.nextLine();
        changeTargetNameService.execute(targetId, newTargetName);
        System.out.println("----------");
        System.out.println("Target name was changed!");
        System.out.println("----------");
        }

}
